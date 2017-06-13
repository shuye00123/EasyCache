package EasyCache.java.cache;

import EasyCache.java.Node;
import EasyCache.java.node.OrderNode;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by shuye on 2017/5/17.
 */
public class LRUCache<K,V> extends AbstractCache<K,V> {
    private ConcurrentHashMap<K, Node> cacheMap;

    private int capacity;

    private boolean TIMEOUT_SWITCH;

    private OrderNode head;

    private OrderNode tail;

    public LRUCache() {
        this(100, false);
    }

    public LRUCache(int capacity) {
        this(capacity, false);
    }

    public LRUCache(boolean TIMEOUT_SWITCH) {
        this(100, TIMEOUT_SWITCH);
    }

    public LRUCache(int capacity, boolean TIMEOUT_SWITCH) {
        this.TIMEOUT_SWITCH = TIMEOUT_SWITCH;
        this.capacity = capacity;
        cacheMap = new ConcurrentHashMap<>();
        head = new OrderNode(null, null, -1);
        tail = new OrderNode(null, null, -1);
        head.setNext(tail);
        tail.setPrev(head);
    }

    @Override
    public ConcurrentHashMap<K, Node> cacheMap() {
        return cacheMap;
    }

    @Override
    public void put(K key, V value) {
        put(key, value, -1);
    }

    public void put(K key, V value, long timeout){
        if (TIMEOUT_SWITCH == false && timeout != -1){
            throw new UnsupportedOperationException();
        }
        if (cacheMap().size() >= capacity){
            OrderNode oldest = head.getNext();
            if (TIMEOUT_SWITCH){
                while (oldest.getNext() != null){
                    if (oldest.isTimeOut()){
                        free(oldest);
                    }
                    oldest = oldest.getNext();
                }
            }
            if (cacheMap().size() >= capacity){
                oldest = head.getNext();
                free(oldest);
            }
        }
        OrderNode node = null;
        if (cacheMap().containsKey(key)){
            node = (OrderNode) cacheMap().get(key);
        }else {
            node = new OrderNode(key, value, timeout);

        }
        moveTail(node);
        cacheMap().put(key, node);
    }

    private void moveTail(OrderNode node) {
        if (node.getNext() != null){
            node.getNext().setPrev(node.getPrev());
            node.getPrev().setNext(node.getNext());
        }
        node.setPrev(tail.getPrev());
        node.setNext(tail);
        tail.getPrev().setNext(node);
        tail.setPrev(node);
    }

    private void free(OrderNode oldest) {
        cacheMap().remove(oldest.getKey());
        pop(oldest);
    }

    private OrderNode pop(OrderNode node) {
        node.getPrev().setNext(node.getNext());
        node.getNext().setPrev(node.getPrev());
        node = null;
        return node;
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
        moveTail(node);
        return (V) node.getValue();
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
