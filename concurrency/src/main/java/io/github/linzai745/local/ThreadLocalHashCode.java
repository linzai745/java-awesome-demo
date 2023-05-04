package io.github.linzai745.local;

public class ThreadLocalHashCode {
    public static void main(String[] args) {
        ThreadLocal<Integer> threadLocal = new ThreadLocal<>();
        ThreadLocal<Integer> tl2 = new ThreadLocal<>();
        ThreadLocal<Integer> tl3 = new ThreadLocal<>();
        ThreadLocal<Integer> tl4 = new ThreadLocal<>();
        Thread t = Thread.currentThread();
        for (int i = 0; i < 10; ++i) {
            threadLocal.set(i);
            System.out.println(threadLocal.get());
        }
    }
}
