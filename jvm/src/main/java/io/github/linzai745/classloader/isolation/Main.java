package io.github.linzai745.classloader.isolation;

import java.lang.reflect.Method;

public class Main {
    public static void main(String[] args) throws Exception {
//        IsolationClassLoader loader = new IsolationClassLoader();
        IsolationCustomClassLoader loader = new IsolationCustomClassLoader(Thread.currentThread().getContextClassLoader().getParent());
        Class<?> clazz = loader.loadClass("io.github.linzai745.classloader.isolation.loading.A", false);
        Method main = clazz.getDeclaredMethod("main", String[].class);
        main.invoke(null, new Object[] {args});
    }
}
