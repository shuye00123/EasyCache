package java.cache;

import java.Node;
import java.node.OrderNode;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by shuye on 2017/5/17.
 */
public class FIFOCache<K,V> extends AbstractCache<K,V> {

    private ConcurrentHashMap<K, Node> cacheMap;

    private int capacity;

    private boolean TIMEOUT_SWITCH;

    private OrderNode head;

    private OrderNode tail;

    public FIFOCache() {
        this(100, false);
    }

    public FIFOCache(int capacity) {
        this(capacity, false);
    }

    public FIFOCache(boolean TIMEOUT_SWITCH) {
        this(100, TIMEOUT_SWITCH);
    }

    public FIFOCache(int capacity, boolean TIMEOUT_SWITCH) {
        this.capacity = capacity;
        this.TIMEOUT_SWITCH = TIMEOUT_SWITCH;
        cacheMap = new ConcurrentHashMap<>();
        head = new OrderNode(null, null, -1);
        tail = new OrderNode(null, null, -1);
        head.setNext(tail);
        tail.setPrev(tail);
    }

    @Override
    public ConcurrentHashMap<K, Node> cacheMap() {
        return cacheMap;
    }

    @Override
    public V put(K key, V value) {
        return put(key, value, -1);
    }

    public V put(K key, V value, long timeout){
        if (cacheMap().size() > capacity){
            OrderNode oldest = head.getNext();
            if (TIMEOUT_SWITCH){
                while (oldest.getNext() != null){
                    if (oldest.isTimeOut()){
                        free(oldest);
                    }
                    oldest = oldest.getNext();
                }
            }
            if (cacheMap().size() > capacity){
                oldest = head.getNext();
                if (oldest.getKey() != null){
                    free(oldest);
                }
            }
        }
        OrderNode node = new OrderNode(key, value, timeout);
        push(node);
        OrderNode result = (OrderNode)cacheMap().put(key, node);
        return (V) result.getValue();
    }

    private void free(OrderNode oldest) {
        cacheMap().remove(oldest.getKey());
        pop(oldest);
    }

    private void push(OrderNode node) {
        tail.getPrev().setNext(node);
        tail.setPrev(node);
        node.setPrev(tail.getPrev());
        node.setNext(tail);
    }

    @Override
    public V get(Object key) {
        OrderNode node = (OrderNode) cacheMap().get(key);
        if (node == null) throw new NullPointerException();
        if (TIMEOUT_SWITCH){
            if (node.isTimeOut()){
                free(node);
                return null;
            }
        }
        return (V) node.getValue();
    }

    private OrderNode pop(OrderNode node) {
        node.getPrev().setNext(node.getNext());
        node.getNext().setPrev(node.getPrev());
        node = null;
        return node;
    }

    @Override
    public V remove(Object key) {
        OrderNode node = (OrderNode) cacheMap().get(key);
        V value = (V) node.getValue();
        cacheMap().remove(key);
        pop(node);
        return value;
    }

    @Override
    public void clear() {
        cacheMap().clear();
    }
}
