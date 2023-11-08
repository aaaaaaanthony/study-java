package 引用;

/**
 * ThreadLocal demo
 */
public class Demo4 {

    static ThreadLocal<User> threadLocal = new ThreadLocal<>();

    public static void main(String[] args) {


        new Thread(() -> {
            threadLocal.set(new User());
            try {
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }).start();


        new Thread(() -> {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

            User user = threadLocal.get();
            System.out.println(user);

        }).start();





    }
}
