package io.github.linzai745.synchronizedUtils;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 用信号量模型实现一个 Semaphore 便于理解
 * 信号量模型：
 *  1. 计数器
 *  2. 等待队列
 *  3. 三个方法 - init()、down()、up()
 */
public class SemaphoreCustom {
    // 计数器
    private long count;

    // 等待队列
    private final BlockingQueue<Runnable> queue;

    // init()
    public SemaphoreCustom(long count) {
        this.count = count;
        queue = new ArrayBlockingQueue<>(100);
    }

    // up()
    public void up() {
        count++;
        if (count >= 0) {
            // 移除等待队列中的某个线程T
            boolean offer = queue.offer(() -> {
            });
            // 唤醒线程T
            new Thread() {
                @Override
                public void run() {
                    System.out.println("Run task!");
                }
            }.start();
        }
    }

    // down()
    public void down() {
        count--;
        if (count < 0) {
            // 将任务放入等待队列
            // 阻塞当前线程
        }
    }

}
