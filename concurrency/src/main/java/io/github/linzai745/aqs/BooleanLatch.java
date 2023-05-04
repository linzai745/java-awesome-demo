package io.github.linzai745.aqs;

import java.util.concurrent.locks.AbstractQueuedSynchronizer;

/**
 * 使用 AQS 自定义同步器
 */
public class BooleanLatch {

    // 同步器类
    private static class Sync extends AbstractQueuedSynchronizer {

        @Override
        protected int tryAcquireShared(int ignore) {
            return (isSignalled() ? 1 : -1);
        }

        @Override
        protected boolean tryReleaseShared(int ignore) {
            setState(1);
            return true;
        }

        private boolean isSignalled() {
            return getState() != 0;
        }
    }

    // 持有自定义同步器对象
    private final Sync sync = new Sync();

    public boolean isSignalled() {
        return sync.isSignalled();
    }

    public void signal() {
        sync.releaseShared(1);
    }

    public void await() throws InterruptedException {
        sync.acquireSharedInterruptibly(1);
    }
}
