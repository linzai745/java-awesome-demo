package io.github.linzai745.classloader.isolation;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

/**
 * 破坏双亲委派模型，实现类隔离
 */
public class IsolationCustomClassLoader extends ClassLoader {
    private final Map<String, String> classPath;

    private final ClassLoader jdkClassLoader;

    public IsolationCustomClassLoader(ClassLoader jdkClassLoader) {
        this.jdkClassLoader = jdkClassLoader;
        classPath = new HashMap<>();
        classPath.put("io.github.linzai745.classloader.isolation.loading.A", "/Users/alin/code/java-awesome-demo/jvm/target/classes/io/github/linzai745/classloader/isolation/loading/A.class");
        classPath.put("io.github.linzai745.classloader.isolation.loading.B", "/Users/alin/code/java-awesome-demo/jvm/target/classes/io/github/linzai745/classloader/isolation/loading/B.class");
    }

    @Override
    protected Class<?> loadClass(String name, boolean resolve) throws ClassNotFoundException {
        Class<?> clz = null;
        try {
            clz = jdkClassLoader.loadClass(name);
        } catch (ClassNotFoundException ignored) {

        }

        if (clz != null) {
            return clz;
        }

        String path = classPath.get(name);
        File file = new File(path);
        if (!file.exists()) {
            throw new ClassNotFoundException();
        }

        byte[] bytes = getClassData(file);
        if (bytes.length == 0) {
            throw new ClassNotFoundException();
        }

        clz = defineClass(name, bytes, 0, bytes.length);
        return clz;
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
