package io.github.linzai745.gc;

/**
 * 验证 GC 发生的规则
 * vm args: -XX:PrintGCDetails
 */
public class GCCase {
    public static void main(String[] args) {
        byte[] allocation1, allocation2;
        allocation1 = new byte[10 * 1024 * 1024];
    }
}
