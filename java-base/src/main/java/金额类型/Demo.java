package 金额类型;

import java.math.BigDecimal;

public class Demo {

    public static void main(String[] args) {
        BigDecimal a = new BigDecimal(10);
        BigDecimal b = new BigDecimal(5);

        int i = a.compareTo(b);
        switch (i) {
            case -1: // 小于
                System.out.println("a < b");
                break;
            case 0: // 等于
                System.out.println("a = b");
                break;
            case 1: // 大于
                System.out.println("a > b");
                break;
        }

        if (a.compareTo(b) != 0) {
            System.out.println("a != b");
        }

        if (a.compareTo(b) != -1){
            System.out.println("a >= b");
        }

        if (a.compareTo(b) != 1) {
            System.out.println("a <= b");
        }
    }
}
