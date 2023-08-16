package demo;

import java.io.IOException;

/**
 * 强引用
 */
public class Demo {

//    public static void main(String[] args) throws IOException {
//        User user = new User();
//        user.setName("anthony");
//        user.setAge(20);
//        System.out.println(user);
//
//        user = null;
//        // 垃圾回收
//        System.gc();
//
//        System.out.println(user);
//
//        // 阻塞main现成,给垃圾回收线程时间执行
//        System.in.read();
//    }

    public static void main(String[] args) throws IOException {
        User user = new User();
        user.setName("anthony");
        user.setAge(20);
        System.out.println(user);

//        user = null;
        // 垃圾回收
        System.gc();

        System.out.println(user);

        // 阻塞main现成,给垃圾回收线程时间执行
        System.in.read();
    }

}
