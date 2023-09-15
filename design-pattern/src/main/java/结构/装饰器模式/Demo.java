package 结构.装饰器模式;

public class Demo
{

    public static void main(String[] args) {

        new Thread(() -> {

            ThreadLocal<String> stringThreadLocal = new ThreadLocal<>();
            stringThreadLocal.set("a");
            stringThreadLocal.set("b");

            ThreadLocal<String> stringThreadLocal2 = new ThreadLocal<>();
            stringThreadLocal2.set("c");
            stringThreadLocal2.set("d");

            System.out.println("12321");
            String s = stringThreadLocal.get();
            System.out.println("321312312");

        }).start();
    }
}
