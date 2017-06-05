package java.cache;

import java.Cache;
import java.Node;
import java.node.CountNode;
import java.node.OrderNode;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by shuye on 2017/5/17.
 */
public class LFUCache<K,V> extends AbstractCache<K,V> {
    private ConcurrentHashMap<K, Node> cacheMap;

    private int capacity;

    private boolean TIMEOUT_SWITCH;

    private CountNode head;

    private CountNode tail;

    public LFUCache() {
        this(100, false);
    }

    public LFUCache(int capacity) {
        this(capacity, false);
    }

    public LFUCache(boolean TIMEOUT_SWITCH) {
        this(100, TIMEOUT_SWITCH);
    }

    public LFUCache(int capacity, boolean TIMEOUT_SWITCH) {
        this.TIMEOUT_SWITCH = TIMEOUT_SWITCH;
        this.capacity = capacity;
        cacheMap = new ConcurrentHashMap<>();
        head = new CountNode(null);
        tail = new CountNode(null);
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
        if (TIMEOUT_SWITCH == false && timeout != -1){
            throw new UnsupportedOperationException();
        }
        if (cacheMap().size() >= capacity){
            CountNode useless = head.getNext();
            if (TIMEOUT_SWITCH){
                while (useless.getNext() != null){
                    freeTimeOutNode(useless);
                    useless = useless.getNext();
                }
            }
            if (cacheMap().size() >= capacity){
                useless = head.getNext();
                freeCountNode(useless);
            }
        }
        OrderNode node = null;
        if (cacheMap().containsKey(key)){
            node = (OrderNode) cacheMap().get(key);
        }else {
            node = new OrderNode(key, value, timeout);
            //todo addtolist
        }
        cacheMap().put(key, node);
        return (V) node.getValue();
    }

    private void freeCountNode(CountNode useless) {

    }

    private void freeTimeOutNode(CountNode useless) {

    }

    @Override
    public V get(Object key) {
        return null;
    }

    @Override
    public V remove(Object key) {
        return null;
    }

    @Override
    public void clear() {

    }
}
