package EasyCache.java.cache;

import EasyCache.java.Node;
import EasyCache.java.node.CountNode;
import EasyCache.java.node.CountOrderNode;

import java.util.LinkedList;
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
        head = new CountNode(null, -1);
        tail = new CountNode(null, -1);
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
        CountOrderNode node = null;
        if (cacheMap().containsKey(key)){
            node = (CountOrderNode) cacheMap().get(key);
        }else {
            node = new CountOrderNode(key, value, timeout);
            cacheMap().put(key, node);
            insertIntoCountList(0, node, head);
        }
        cacheMap().put(key, node);
    }

    private void insertIntoCountList(int count, CountOrderNode node, CountNode countNode) {
        while (true){
            if (countNode.getCount() == count){
                countNode.getKey().add(node);
                return;
            }
            if (countNode.getCount() > count || countNode.getNext() == tail){
                List<Node> list = new LinkedList<>();
                list.add(node);
                CountNode newNode = new CountNode(list, count);
                newNode.setPrev(countNode.getPrev());
                newNode.setNext(countNode);
                countNode.getPrev().setNext(newNode);
                countNode.setPrev(newNode);
                return;
            }
            countNode = countNode.getNext();
        }

    }

    private void freeCountNode(CountNode useless) {
        List<Node> list = useless.getKey();
        CountOrderNode node = (CountOrderNode)list.get(0);
        cacheMap().remove(node.getKey());
        if (list.size() == 1){
            removeCountNode(useless);
        }else {
            list.remove(0);
        }
    }

    private void freeTimeOutNode(CountNode useless) {
        List<Node> list = useless.getKey();
        for (Node node : list) {
            if (((CountOrderNode)node).isTimeOut()){
                list.remove(node);
            }
        }
        if (list.size() == 0){
            removeCountNode(useless);
        }
    }

    private void removeCountNode(CountNode node){
        node.getPrev().setNext(node.getNext());
        node.getNext().setPrev(node.getPrev());
    }

    @Override
    public V get(Object key) {
        CountOrderNode node = (CountOrderNode) cacheMap().get(key);
        if (node == null) throw new NullPointerException();
        CountNode countNode = findCountNode(node.getCount());
        if (TIMEOUT_SWITCH){
            if (node.isTimeOut()){
                cacheMap().remove(key);
                if (countNode == null) throw new NullPointerException();
                freeTimeOutNode(countNode);
                return null;
            }
        }
        int count = node.increment();
        countNode.getKey().remove(node);
        insertIntoCountList(count, node, countNode);
        return (V) node.getValue();
    }

    private CountNode findCountNode(int count) {
        CountNode countNode = head.getNext();
        while (countNode.getNext() != null){
            if (countNode.getCount() == count){
                return countNode;
            }
        }
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
