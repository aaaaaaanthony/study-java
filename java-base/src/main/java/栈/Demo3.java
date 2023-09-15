package 栈;

/**
 * 局部变量线程安全演示
 */
public class Demo3 {

    static int x = 0;


    // 多线程运行
    public void demo() {
        for (int i = 0; i < 1000; i++) {
            x++;
        }
        System.out.println(x);
    }
}
