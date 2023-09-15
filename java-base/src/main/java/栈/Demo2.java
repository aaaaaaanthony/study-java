package 栈;

/**
 * 栈溢出
 */
public class Demo2 {

    private static int count = 0;

    public static void main(String[] args) {
        try {
            method();
        } catch (Throwable e) {
            e.printStackTrace();
            System.out.println(count);
        }
    }

    private static void  method(){

        count++;
        method();

    }


}
