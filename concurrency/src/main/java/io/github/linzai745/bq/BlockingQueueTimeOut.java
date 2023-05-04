package io.github.linzai745.bq;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

public class BlockingQueueTimeOut {
    private final static BlockingQueue<Integer> queue = new LinkedBlockingQueue<>() {{
        try {
            put(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }};

    public static void main(String[] args) throws InterruptedException {
        Integer i = queue.poll(10, TimeUnit.SECONDS);
        System.out.println(i);
        i = queue.poll(10, TimeUnit.SECONDS);
        System.out.println(i);
    }
}
