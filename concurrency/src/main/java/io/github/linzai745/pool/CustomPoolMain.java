package io.github.linzai745.pool;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.TimeUnit;

public class CustomPoolMain {
    public static void main(String[] args) {
        CustomThreadPool pool = new CustomThreadPool(1, 2, 10, TimeUnit.SECONDS, new ArrayBlockingQueue<>(10));

        pool.shutdown();
    }
}
