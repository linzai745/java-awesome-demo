package io.github.linzai745.classloader.isolation.loading;

public class B {
    public void showClassLoader() {
        System.out.println("B class: " + this.getClass().getClassLoader());
    }
}
