package EasyCache.java.cache;

import EasyCache.java.Cache;
import EasyCache.java.Node;

import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by shuye on 2017/5/17.
 */
public abstract class AbstractCache<K,V> implements Cache<K,V> {
    public abstract ConcurrentHashMap<K, Node> cacheMap();

    public abstract void put(K key, V value);

    public abstract V get(Object key);

    public int size(){
        return cacheMap().size();
    }

    public boolean contains(Object key){
        return cacheMap().containsKey(key);
    }

    public abstract V remove(Object key);

    public abstract void clear();

    public boolean isEmpty(){
        return cacheMap().isEmpty();
    }
}
