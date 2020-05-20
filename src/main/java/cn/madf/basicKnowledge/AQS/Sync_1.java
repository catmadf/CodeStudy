package cn.madf.basicKnowledge.AQS;

import java.util.concurrent.locks.AbstractQueuedLongSynchronizer;

/**
 * @author 烛影鸾书
 * @date 2020/5/11
 * @copyright© 2020
 */
public class Sync_1 extends AbstractQueuedLongSynchronizer {

    @Override
    protected boolean tryAcquire(long arg) {
        assert arg == 1;
        if (compareAndSetState(0, 1)){
            setExclusiveOwnerThread(Thread.currentThread());
            return true;
        }
        return false;
    }

    @Override
    protected boolean tryRelease(long arg) {
        assert arg == 1;
        if (!isHeldExclusively()) throw new IllegalMonitorStateException();
        setExclusiveOwnerThread(null);
        setState(0);
        return true;
    }

    @Override
    protected boolean isHeldExclusively() {
        return getExclusiveOwnerThread() == Thread.currentThread();
    }
}
