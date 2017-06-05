package java.node;

import java.Node;
import java.util.Date;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by shuye on 2017/5/17.
 */
public class CountNode implements Node {
    private List<Node> key;
    private AtomicInteger count;

    private CountNode next;
    private CountNode prev;

    public CountNode(List<Node> key) {
        this.key = key;
        count = new AtomicInteger(0);
        this.next = null;
        this.prev = null;
    }

    public int increment(){
        return count.incrementAndGet();
    }

    public List<Node> getKey() {
        return key;
    }

    public void setKey(List<Node> key) {
        this.key = key;
    }

    public int getCount() {
        return count.intValue();
    }

    public CountNode getNext() {
        return next;
    }

    public void setNext(CountNode next) {
        this.next = next;
    }

    public CountNode getPrev() {
        return prev;
    }

    public void setPrev(CountNode prev) {
        this.prev = prev;
    }
}
