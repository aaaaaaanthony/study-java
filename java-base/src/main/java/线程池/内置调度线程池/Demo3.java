package 线程池.内置调度线程池;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;

/**
 * 测试延迟执行和重复执行的功能
 */
public class Demo3 {

    public static void main(String[] args) {
        // 获取具备延迟执行任务的线程池对象
        ScheduledExecutorService es = Executors.newSingleThreadScheduledExecutor( new ThreadFactory() {

            int n = 1;
            @Override
            public Thread newThread(Runnable r) {
                return new Thread("自定义线程:" + n++);
            }
        });


        // 创建多个任务,并且提交任务,每个任务延迟2s执行
        es.scheduleWithFixedDelay(new MyRunable2(1), 1,2, TimeUnit.SECONDS);
        System.out.println("结束");


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
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        System.out.println("线程:"+name + "执行了任务:"+i);
    }
}
