package java.node;

import java.Node;

/**
 * Created by shuye on 2017/5/17.
 */
public class TypeNode implements Node {
    private Object key;
    private int count;
    private TypeNode next;
    private TypeNode prev;

    public TypeNode(Object key) {
        this.key = key;
        this.count = 0;
        this.next = null;
        this.prev = null;
    }

    public Object getKey() {
        return key;
    }

    public void setKey(Object key) {
        this.key = key;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public TypeNode getNext() {
        return next;
    }

    public void setNext(TypeNode next) {
        this.next = next;
    }

    public TypeNode getPrev() {
        return prev;
    }

    public void setPrev(TypeNode prev) {
        this.prev = prev;
    }
}
