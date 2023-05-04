package io.github.linzai745.local;

import java.util.concurrent.atomic.AtomicInteger;

public class LocalHashCode {
    private final int threadLocalHashCode = nextHashCode();

    private static AtomicInteger nextHashCode = new AtomicInteger();

    private static final int HASH_INCREMENT = 0x61c88647;

    public static void main(String[] args) {
        LocalHashCode instance = new LocalHashCode();
        for (int i = 0; i < 10; ++i) {
            System.out.println(instance.getThreadLocalHashCode());
        }
    }

    public int getThreadLocalHashCode() {
        return threadLocalHashCode;
    }

    private static int nextHashCode() {
        return nextHashCode.getAndAdd(HASH_INCREMENT);
    }
}
