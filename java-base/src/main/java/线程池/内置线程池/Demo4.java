package 线程池.内置线程池;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;

public class Demo4 {
    public static void main(String[] args) {

//        test1();
        test2();

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
            executorService.submit(new MyRunable4(i));
        }
        // 立刻关闭线程池,如果线程池中的还有缓存任务,没有执行,则取消执行,并返回这些任务
        List<Runnable> runnables = executorService.shutdownNow();
        System.out.println(runnables);
        // 不能再接收新的方法了,会报错
        executorService.submit(new MyRunable4(888));

    }

    private static void test1() {
        ExecutorService executorService = Executors.newSingleThreadExecutor();

        for (int i = 0; i < 10; i++) {
            executorService.submit(new MyRunable4(i));
        }
        // 关闭线程池,仅仅是不再接收新的任务,以前的任务还会记录执行
        executorService.shutdown();
        // 不能再接收新的方法了,会报错
        executorService.submit(new MyRunable4(888));

    }
}


class MyRunable4 implements Runnable {

    int i = 0;

    public MyRunable4(int i) {
        this.i = i;
    }

    @Override
    public void run() {
        String name = Thread.currentThread().getName();
        System.out.println("线程:"+name + "执行了任务:"+i);
    }

    @Override
    public String toString() {
        return "MyRunable4{" +
                "i=" + i +
                '}';
    }
}
