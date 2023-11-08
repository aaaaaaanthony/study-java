package 线程池.手写线程池;

public class MyTest {
    public static void main(String[] args) {
        MyThreadPool myThreadPool = new MyThreadPool(2, 4, 20);
        for (int i = 0; i < 10; i++) {
            MyTask myTask = new MyTask(i);
            myThreadPool.submit(myTask);
        }
    }
}
