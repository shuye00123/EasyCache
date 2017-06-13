package EasyCache.java.node;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by shuye on 2017/6/8.
 */
public class CountOrderNode extends OrderNode {
    private AtomicInteger count;
    public CountOrderNode(Object key, Object value, long timeOut) {
        super(key, value, timeOut);
        count = new AtomicInteger(0);
    }

    public int increment(){
        return count.incrementAndGet();
    }

    public int getCount() {
        return count.intValue();
    }

}
