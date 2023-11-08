package 线程池.手写线程池;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * 自定义线程池类
 */
public class MyThreadPool {

    // 任务队列
    private List<Runnable> tasks = Collections.synchronizedList(new LinkedList<>());
    // 当前线程数量
    private int num;
    // 核心线程数量
    private int corePoolSize;
    // 最大线程数量
    private int maxSize;
    // 任务队列的长度
    private int workSize;

    public MyThreadPool(int corePoolSize, int maxSize, int workSize) {
        this.corePoolSize = corePoolSize;
        this.maxSize = maxSize;
        this.workSize = workSize;
    }

    // 提交方法-将任务添加到队列中
    public void submit(Runnable runnable){
        // 判断当前队列的数量,是否超出了最大任务数量
        if (tasks.size() >= workSize) {
            System.out.println("任务"+runnable+"被丢弃了");
        }else {
            tasks.add(runnable);

            // 执行方法-判断当前线程的数量,决定创建核心线程数量还是非线程数量
            execTask(runnable);
        }
    }

    private void execTask(Runnable runnable) {
        // 判断当前线程中的线程总数量,是否超出了核心线程数
        if (num < corePoolSize) {
            // 创建核心线程
            new MyWorker("核心线程:"+num, tasks).start();
            num++;
        } else if (num < maxSize) {
            // 创建非核心线程
            new MyWorker("非核心线程:"+num, tasks).start();
            num++;
        }else {
            System.out.println("任务:"+runnable+"被缓存了....");
        }
    }
}
