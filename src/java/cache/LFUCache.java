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

    private ConcurrentHashMap<Integer, List<Node>> countMap;

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
        countMap = new ConcurrentHashMap<>();
        head = new CountNode(null, null, -1);
        tail = new CountNode(null, null, -1);
        head.setNext(tail);
        tail.setPrev(tail);
    }

    @Override
    public ConcurrentHashMap<K, Node> cacheMap() {
        return cacheMap;
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
