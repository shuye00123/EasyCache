package java.node;

import java.Node;
import java.util.Date;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by shuye on 2017/5/17.
 */
public class CountNode implements Node {
    private Object key;
    private Object value;
    private AtomicInteger count;
    private long TimeOut;
    private long timestamp;
    private CountNode next;
    private CountNode prev;

    public CountNode(Object key, Object value, long timeOut) {
        this.key = key;
        this.value = value;
        count = new AtomicInteger(0);
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

    public int increment(){
        return count.incrementAndGet();
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

    public long getTimeOut() {
        return TimeOut;
    }

    public void setTimeOut(long timeOut) {
        TimeOut = timeOut;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }
}
