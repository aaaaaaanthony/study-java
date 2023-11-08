package 线程池.内置调度线程池;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * 测试延迟执行和重复执行的功能
 */
public class Demo {

    public static void main(String[] args) {
        // 获取具备延迟执行任务的线程池对象
        ScheduledExecutorService es = Executors.newScheduledThreadPool(3);


        for (int i = 0; i < 10; i++) {
            // 创建多个任务,并且提交任务,每个任务延迟2s执行
            es.schedule(new MyRunable(i), 2, TimeUnit.SECONDS);
        }
        System.out.println("结束");


    }
}

class MyRunable implements Runnable {

    int i = 0;

    public MyRunable(int i) {
        this.i = i;
    }

    @Override
    public void run() {
        String name = Thread.currentThread().getName();
        System.out.println("线程:"+name + "执行了任务:"+i);
    }
}
