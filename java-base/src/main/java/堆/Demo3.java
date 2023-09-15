package 堆;

import java.util.ArrayList;

/**
 * 演示jvisualvm
 */
public class Demo3 {
    public static void main(String[] args) throws InterruptedException {

        ArrayList<Persion> persions = new ArrayList<>();
        for (int i = 0; i < 200; i++) {
            persions.add(new Persion());
        }

        Thread.sleep(99999999999999999L);
    }
}

class Persion{
    private byte[] big = new byte[1024 * 1024];
}
