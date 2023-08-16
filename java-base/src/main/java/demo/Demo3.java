package demo;

import java.lang.ref.WeakReference;

/**
 * 软应用
 */
public class Demo3 {

    public static void main(String[] args) throws InterruptedException {

        WeakReference<User> weakReference = new WeakReference<>(new User());
        System.out.println(weakReference.get());
        // 看到软引用就干掉
        System.gc();

        Thread.sleep(1000);
        System.out.println(weakReference.get());


        ThreadLocal<User> threadLocal = new ThreadLocal<>();
        threadLocal.set(new User());
        threadLocal.remove();

    }
}
