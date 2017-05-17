package java;


/**
 * Created by shuye on 2017/5/17.
 */
public class OrderNode {
    private Object key;
    private long TimeOut;
    private OrderNode next;
    private OrderNode prev;

    public OrderNode(Object key, long timeOut) {
        this.key = key;
        TimeOut = timeOut;
        this.next = null;
        this.prev = null;
    }

    public Object getKey() {
        return key;
    }

    public void setKey(Object key) {
        this.key = key;
    }

    public long getTimeOut() {
        return TimeOut;
    }

    public void setTimeOut(long timeOut) {
        TimeOut = timeOut;
    }

    public OrderNode getNext() {
        return next;
    }

    public void setNext(OrderNode next) {
        this.next = next;
    }

    public OrderNode getPrev() {
        return prev;
    }

    public void setPrev(OrderNode prev) {
        this.prev = prev;
    }
}
