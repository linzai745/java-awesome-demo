package io.github.linzai745.StringTable;

/**
 * 测试字符串常量池中的对象是否相同, 字符串常量池只对String 对象生效
 */
public class StringTableConstantPool {
    public static void main(String[] args) {
        String aa = "ab";

        String bb = "ab";

        // 创建新的对象
        String cc = new String("ab");

        String dd = cc.intern();

        System.out.println(aa == bb);
        System.out.println(aa == dd);
    }
}
