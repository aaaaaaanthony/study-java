package 线程池.手写线程池;

import java.util.List;

/**
 * 编写一个线程类,设计一个属性,用于保存线程的名字
 * 设计一个集合,用于保存所有的任务
 */
public class MyWorker extends Thread{

    private List<Runnable> tasks;

    public MyWorker(String name, List<Runnable> tasks) {
        super.setName(name);
        this.tasks = tasks;
    }

    @Override
    public void run() {
        // 判断集合中是否有任务,如果有就一直运行
        while (!tasks.isEmpty()) {
            Runnable remove = tasks.remove(0);
            remove.run();
        }
    }
}
