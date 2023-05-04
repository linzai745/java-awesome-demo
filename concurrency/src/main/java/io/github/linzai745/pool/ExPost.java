package io.github.linzai745.pool;

public class ExPost {
    public static void main(String[] args) throws Exception {
        try {
            System.out.println("throws ex!");
            throw new RuntimeException();
        } catch (Exception e) {
            System.out.println("catch!!!");
        } finally {
            System.out.println("final!!!!");
        }
    }
}
