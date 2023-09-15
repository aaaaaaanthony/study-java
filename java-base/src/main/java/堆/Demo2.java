package 堆;

public class Demo2 {
    public static void main(String[] args) throws InterruptedException {
        System.out.println("1.....");
        Thread.sleep(30000);
        byte[] bytes = new byte[1028 * 1024 * 10];
        System.out.println("2.....");
        Thread.sleep(30000);
        bytes = null;
        System.gc();
        System.out.println("3.....");
        Thread.sleep(1000000);
    }
}
