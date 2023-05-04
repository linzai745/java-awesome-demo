package io.github.linzai745.synchronizedUtils;

import java.util.concurrent.CountDownLatch;

/**
 * 使用 CountDownLatch 示例
 */
public class CountDownLatchDemo {

    public static void main(String[] args) throws InterruptedException {
        Harness harness = new Harness();
        harness.timeTasks(10, new Runnable() {
            @Override
            public void run() {
                System.out.println("Hello, CountDownLatch!");
            }
        });
    }


    private static class Harness {
        public long timeTasks(int nThreads, Runnable task) throws InterruptedException {
            final CountDownLatch startGate = new CountDownLatch(1);
            final CountDownLatch endGate = new CountDownLatch(nThreads);

            for (int i = 0; i < nThreads; i++) {
                Thread t = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            startGate.await();
                            try {
                                task.run();
                            } finally {
                                endGate.countDown();
                            }
                        } catch (InterruptedException ignored) {

                        }
                    }
                });
                t.start();
            }

            long start = System.nanoTime();
            startGate.countDown();
            endGate.await();
            long end = System.nanoTime();
            return end - start;
        }
    }
}
