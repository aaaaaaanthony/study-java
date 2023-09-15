package 常量池;


/**
 * -Xmx10m -XX:+PrintStringTableStatistics -XX:+PrintGCDetails -verbose:gc
 *
 *
 * PrintStringTableStatistics: 打印字符串池的使用情况
 * PrintGCDetails: 打印GC信息
 */
public class Demo3 {
    public static void main(String[] args) {

        int i = 0;
        try {
            for (int j = 0; j < 10000; j++) {
                String.valueOf(j).intern();
                i++;
            }

        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }finally {
            System.out.println(i);
        }

    }
}
