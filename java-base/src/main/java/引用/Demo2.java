package 引用;

import java.io.IOException;
import java.lang.ref.SoftReference;

/**
 * 软引用
 */
public class Demo2 {

    public static void main(String[] args) throws IOException, InterruptedException {

        // 程序系统的时候JVM记得设置   -Xmx20M

        // 生成一个10MB的空间
        // sr是强引用
        // SoftReference这个对象是软应用
        // 这个时候,内容大约是 20M-10M-JVM(4~5M) = 5M的剩余空间
        SoftReference<byte[]> sr = new SoftReference<>(new byte[1024 * 1024 *10]);
        // 是能正常获取到数据
        System.out.println(sr.get());
        System.gc();

        Thread.sleep(1000);
        // 是能正常获取到数据
        System.out.println(sr.get());


        // 5M-12M 不够地方放了,就把原来的软引用的数据清掉,5M-12M+10M =3M
        // 再生成一个12MB的数据
        byte[] arr2 = new byte[1024 * 1024 * 12];
        // 原来的软引用的数据就会被清掉了,然后放arr2的数据,打印为null
        System.out.println(sr.get());

        // 3M-arr2的12M+arr3的20M 还是不狗放
        // 再生成一个20MB的数据
        byte[] arr3 = new byte[1024 * 1024 * 20];
        // 软引用都清掉了,还是不够放,java.lang.OutOfMemoryError: Java heap space
        System.out.println(sr.get());

    }


}
