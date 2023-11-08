package 线程池.内置线程池;

import java.util.concurrent.*;

public class Demo5 {

    public static void main(String[] args) throws ExecutionException, InterruptedException, TimeoutException {
        // 创建线程池对象
        ExecutorService executorService = Executors.newCachedThreadPool();
        Future<Integer> submit = executorService.submit(new MyCall(1, 2));
//        test1(submit);
//        test2(submit);
    }

    private static void test2(Future<Integer> submit) throws InterruptedException, ExecutionException, TimeoutException {
        Thread.sleep(1000);
        System.out.println("取消任务执行的结果" + submit.cancel(true));

        // 主线程等待超时
        Integer i = submit.get(1, TimeUnit.SECONDS);
        System.out.println("任务执行的结果:" + i);
    }

    private static void test1(Future<Integer> submit) throws InterruptedException, ExecutionException {
        // 判断任务是否完成
        boolean done = submit.isDone();
        System.out.println("第一次判断任务是否完成:" + done);

        boolean cancelled = submit.isCancelled();
        System.out.println("第一次判断任务是否取消:" + cancelled);

        // 无限期等待
        Integer i = submit.get();
        System.out.println("任务执行的结果:" + i);

        System.out.println("第二  次判断任务是否完成:" + submit.isDone());
        System.out.println("第一次判断任务是否取消:" + submit.isCancelled());
    }
}

class MyCall implements Callable<Integer> {

    int a;
    int b;

    public MyCall(int a, int b) {
        this.a = a;
        this.b = b;
    }

    @Override
    public Integer call() throws Exception {
        String name = Thread.currentThread().getName();
        System.out.println("线程:"+name + "准备开始计算:");
        Thread.sleep(2000);
        System.out.println("线程:"+name + "计算完成:");
        return a+b;
    }
}
