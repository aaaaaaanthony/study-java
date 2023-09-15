package gc;

import java.io.IOException;
import java.util.ArrayList;

/**
 * 使用mat 演示GC root
 */
public class Demo {
    public static void main(String[] args) throws IOException {

        ArrayList<Object> list1 = new ArrayList<>();
        list1.add("a");
        list1.add("b");
        System.out.println(1);
        // jsp
        // jmap -dump:format=b,live,file=1.bin 进程id

        System.in.read();

        list1 = null;
        System.out.println(2);
        System.in.read();
        System.out.println("end....");
    }
}
