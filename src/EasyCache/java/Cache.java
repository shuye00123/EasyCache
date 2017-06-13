package EasyCache.java;

/**
 * Created by shuye on 2017/5/17.
 */
public interface Cache<K,V> {

    void put(K key, V value);

    V get(K key);

    int size();

    boolean contains(Object key);

    V remove(Object key);

    void clear();

    boolean isEmpty();

}
