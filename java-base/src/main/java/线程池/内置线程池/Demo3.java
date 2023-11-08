package 线程池.内置线程池;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;

public class Demo3 {
    public static void main(String[] args) {
//        test1();
        test2();
    }
    private static void test1() {
        ExecutorService executorService = Executors.newSingleThreadExecutor();

        for (int i = 0; i < 10; i++) {
            executorService.submit(new MyRunable3(i));
        }
    }

    private static void test2() {
        ExecutorService executorService = Executors.newSingleThreadExecutor(new ThreadFactory() {

            int i=1;
            @Override
            public Thread newThread(Runnable r) {
                return new Thread(r, "自定义线程名字:" + i++);
            }

        });

        for (int i = 0; i < 10; i++) {
            executorService.submit(new MyRunable3(i));
        }
    }
}


class MyRunable3 implements Runnable {

    int i = 0;

    public MyRunable3(int i) {
        this.i = i;
    }

    @Override
    public void run() {
        String name = Thread.currentThread().getName();
        System.out.println("线程:"+name + "执行了任务:"+i);
    }
}
