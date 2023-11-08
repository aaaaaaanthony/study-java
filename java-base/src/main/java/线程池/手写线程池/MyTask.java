package 线程池.手写线程池;

/**
 * 包含任务编号(id),每一个任务执行时间需要0.2s
 */
public class MyTask implements Runnable {

    private int id;

    public MyTask(int id) {
        this.id = id;
    }

    @Override
    public void run() {
        String name = Thread.currentThread().getName();
        System.out.println("线程:"+name + "即将开始任务:" + id);
        try {
            Thread.sleep(200);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println("线程:"+name + "完成了任务:" + id);
    }

    @Override
    public String toString() {
        return "MyTask{" + "id=" + id + '}';
    }
}
