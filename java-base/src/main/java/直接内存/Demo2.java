package 直接内存;

import java.io.IOException;
import java.nio.ByteBuffer;

public class Demo2 {
    static int _1GB = 1024 * 1024 * 1024;

    public static void main(String[] args) throws IOException {
        ByteBuffer byteBuffer = ByteBuffer.allocateDirect(_1GB);
        System.out.println("分配完毕"); // 看任务管理器,可以看到这个程序占用1G+
        System.in.read();

        System.out.println("开始释放");
        byteBuffer = null;
        System.gc();  // 可以看到被会回收了
        System.in.read();
    }
}
