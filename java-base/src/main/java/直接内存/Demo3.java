package 直接内存;

import sun.misc.Unsafe;

import java.io.IOException;
import java.lang.reflect.Field;
import java.nio.ByteBuffer;

public class Demo3 {
    static int _1GB = 1024 * 1024 * 1024;

    public static void main(String[] args) throws IOException, NoSuchFieldException, IllegalAccessException {
        Unsafe unsafe = getUnsafe();
        // 分配内存
        // base 表示内存地址
        long base = unsafe.allocateMemory(_1GB);
        unsafe.setMemory(base,_1GB,(byte)0);
        System.in.read();

        // 释放内存
        unsafe.freeMemory(base);
        System.in.read();
    }

    public static Unsafe getUnsafe() throws NoSuchFieldException, IllegalAccessException {

        Field declaredField = Unsafe.class.getDeclaredField("theUnsafe");
        declaredField.setAccessible(true);
        return (Unsafe) declaredField.get(null);
    }
}
