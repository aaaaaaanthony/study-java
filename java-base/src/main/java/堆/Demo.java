package 堆;

import java.util.ArrayList;
import java.util.List;

/**
 * 演示oom
 * lang.OutOfMemoryError: Overflow: String length out of range
 */
public class Demo {

    public static void main(String[] args) {


        int i = 0;

        List<String> List = new ArrayList<>();
        String a = "hello";

        try {
            while (true) {
                List.add(a);
                a = a + a;
                i++;
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(i);

        }


    }
}
