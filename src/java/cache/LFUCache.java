package java.cache;

import java.Cache;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by shuye on 2017/5/17.
 */
public class LFUCache<K,V> extends AbstractCache<K,V> {

    @Override
    public ConcurrentHashMap<K, V> cacheMap() {
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
