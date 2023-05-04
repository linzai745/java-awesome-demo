package io.github.linzai745.local;

import java.text.SimpleDateFormat;
import java.util.Random;

/**
 * 初始化不带默认值
 */
public class InstantThreadLocal {

    private static final ThreadLocal<SimpleDateFormat> formatter = new ThreadLocal<>();

    public static void main(String[] args) throws InterruptedException {
        for (int i = 0; i < 10; i++) {
            Thread t = new Thread(() -> {
                formatter.set(new SimpleDateFormat());
                System.out.println(Thread.currentThread().getName() + " default Formatter = " + formatter.get().toPattern());
                try {
                    Thread.sleep(new Random().nextInt(1000));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
//                formatter.set(new SimpleDateFormat());
//                System.out.println(Thread.currentThread().getName() + " default Formatter = " + formatter.get().toPattern());
            });
            Thread.sleep(new Random().nextInt(1000));
            t.start();
        }
    }
}
