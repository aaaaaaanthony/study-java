package 常量池;

// StringTable["a","b","ab"]
public class Demo {
    // 反编译之后,常量池中的信息,都会被加载到运行时常量池中,这时都是常量池中的符号,还没有变成Java字符串对象
    // 运行到s1这行代码的时候,就会把a符号变成a字符串对象,存入到stringtable[]中
    // 用到才会创建字符串对象放到串池中,也就是懒加载
    public static void main(String[] args) {

        String s1 = "a"; // 懒加载
        String s2 = "b";
        String s3 = "ab";

        // 字符串相加的原理:new StringBuilder().append(s1).append(s2).toString()
        // 上面s3的位置是在串池中,s4 是在堆内存中
        String s4 = s1 + s2;
        // false
        System.out.println(s3 == s4);

        // javac在编译期间就确定为ab了,运行的时候直接去串池中查找
        String s5 = "a" + "b";
        // true
        System.out.println(s3 == s5);

    }
}
