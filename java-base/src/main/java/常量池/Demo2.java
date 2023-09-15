package 常量池;

public class Demo2 {
    public static void main(String[] args) {
        String s = new String("a") + new String("b");

        // 讲字符串对象尝试放入到串池中,如果有不会放入,如果没有则会放入串池,会把串池中的对象返回
        String intern = s.intern();
        // true
        System.out.println(intern=="ab");
        // true
        System.out.println(s=="ab");
    }
}
