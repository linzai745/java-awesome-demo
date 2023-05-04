package io.github.linzai745.pool;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ThreadPoolDemo {
    public static void main(String[] args) {
        ThreadPoolExecutor executor = new ThreadPoolExecutor(1, 2, 10, TimeUnit.SECONDS, new ArrayBlockingQueue<>(5));

        for (int i = 0; i < 7; ++i) {
            int taskId = i;
            executor.execute(new Runnable() {
                @Override
                public void run() {
                    System.out.println("Task " + taskId);
                }
            });
        }

        System.out.println("Waiting Debug.");
        System.out.println("Next debug.");
    }
}
