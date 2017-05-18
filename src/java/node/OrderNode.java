package java.node;


import java.Node;
import java.util.Date;

/**
 * Created by shuye on 2017/5/17.
 */
public class OrderNode implements Node {
    private Object key;
    private Object value;
    private long TimeOut;
    private long timestamp;
    private OrderNode next;
    private OrderNode prev;

    public OrderNode(Object key,Object value, long timeOut) {
        this.key = key;
        this.value = value;
        TimeOut = timeOut;
        timestamp = new Date().getTime();
        this.next = null;
        this.prev = null;
    }

    public boolean isTimeOut(){
        if (TimeOut == -1){
            return false;
        }
        long now = new Date().getTime();
        if (now >= TimeOut + timestamp){
            return true;
        }
        return false;
    }

    public Object getKey() {
        return key;
    }

    public void setKey(Object key) {
        this.key = key;
    }

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
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
