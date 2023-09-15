package 直接内存;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;

public class Demo {

    static int _100MB = 1024 * 1024 * 100;
    public static void main(String[] args) {

        List<ByteBuffer> objects = new ArrayList<>();

        int i = 0;

        try {
            while (true) {
                ByteBuffer allocate = ByteBuffer.allocateDirect(_100MB);
                objects.add(allocate);
                i++;
            }
        } finally {
            System.out.println(i);
        }

    }
}
