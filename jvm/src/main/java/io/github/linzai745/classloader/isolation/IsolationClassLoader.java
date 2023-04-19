package io.github.linzai745.classloader.isolation;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

/**
 * 自定义类加载器应用场景：实现Java 类隔离, 实现不同的类通过不同的类加载器来完成加载。
 * 原理：
 *  1. 不同的类通过不同的类加载器完成加载。
 *  2. JVM 会根据 “类加载器 + 类全名” 唯一标识一个类。
 *
 * 流程：
 * 1. 首先定义两个类，A 类会打印出自己的类加载器。
 * 2. 然后调用 B 类打印它的类加载器。
 * 3. 预期是自定义重写了findClass 方法的类加载器 IsolationClassLoader 能够加载 A 类和 B 类。
 *
 * 下面这种方式不会破坏双亲委派模型
 *
 */
public class IsolationClassLoader extends ClassLoader {

    private final Map<String, String> classPath;

    public IsolationClassLoader() {
        classPath = new HashMap<>();
        classPath.put("io.github.linzai745.classloader.isolation.loading.A", "/Users/alin/code/java-awesome-demo/jvm/target/classes/io/github/linzai745/classloader/isolation/loading/A.class");
        classPath.put("io.github.linzai745.classloader.isolation.loading.B", "/Users/alin/code/java-awesome-demo/jvm/target/classes/io/github/linzai745/classloader/isolation/loading/B.class");
    }

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        String path = this.classPath.get(name);
        File file = new File(path);
        if (!file.exists()) {
            throw new ClassNotFoundException();
        }

        byte[] bytes = getClassData(file);
        if (bytes == null || bytes.length == 0) {
            throw new ClassNotFoundException();
        }
        return defineClass(name, bytes, 0, bytes.length);
    }

    private byte[] getClassData(File file) {
        try (InputStream ins = new FileInputStream(file); ByteArrayOutputStream baos = new ByteArrayOutputStream()) {
            byte[] buffer = new byte[4096];
            int bytesNumRead = 0;
            while ((bytesNumRead = ins.read(buffer)) != -1) {
                baos.write(buffer, 0, bytesNumRead);
            }
            return baos.toByteArray();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new byte[0];
    }
}
