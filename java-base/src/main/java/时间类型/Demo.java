package 时间类型;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Demo {

    public static void main(String[] args) throws ParseException {

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date begin = sdf.parse("2020-01-01 00:01:00");
        Date end = sdf.parse("2020-01-01 00:02:00");

        // 使用compareTo方法
        int i = begin.compareTo(end);
        switch (i) {
            case -1:
                System.out.println("开始时间大于结束时间");
                break;
            case 0:
                System.out.println("开始时间等于结束时间");
                break;
            case 1:
                System.out.println("开始时间小于结束时间");
                break;
        }

        // 使用before和after方法
        boolean b = begin.before(end);
        System.out.println(b);

        boolean c = begin.after(end);
        System.out.println(c);
    }
}
