package java.cache;

import java.Cache;
import java.Node;
import java.node.OrderNode;
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
        tail.setPrev(tail);
    }

    @Override
    public ConcurrentHashMap<K, Node> cacheMap() {
        return null;
    }

    @Override
    public V put(K key, V value) {
        return null;
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
