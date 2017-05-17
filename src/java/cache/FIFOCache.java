package java.cache;

import java.Cache;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by shuye on 2017/5/17.
 */
public class FIFOCache<K,V> extends AbstractCache<K,V> {

    private ConcurrentHashMap<K,V> cacheMap;

    public FIFOCache() {
        cacheMap = new ConcurrentHashMap<>();
    }

    @Override
    public ConcurrentHashMap<K, V> cacheMap() {
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
