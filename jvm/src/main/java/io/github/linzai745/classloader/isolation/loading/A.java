package io.github.linzai745.classloader.isolation.loading;

/**
 * 为了简单，这里只输出加载 A 类的加载器类信息。
 */
public class A {
    public static void main(String[] args) {
        A a = new A();
        a.showClassLoader();
    }

    public void showClassLoader() {
        System.out.println("A class: " + this.getClass().getClassLoader());
        B b = new B();
        b.showClassLoader();
    }
}
