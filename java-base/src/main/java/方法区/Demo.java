//package 方法区;
//
//
//import jdk.internal.org.objectweb.asm.ClassWriter;
//import jdk.internal.org.objectweb.asm.Opcodes;
//
///**
// * 演示元空间内存溢出
// * 方法区内存溢出,1.8才有这个版本
// * -XX:MaxMetaspaceSize=8m
// *
// * ClassLoader:类加载器,可以用来加载类的二进制字节码
// */
//public class Demo extends ClassLoader{
//    public static void main(String[] args) {
//
//        int j = 0;
//
//        try {
//
//            Demo test = new Demo();
//
//            for (int i = 0; i < 10000; i++, j++) {
//
//                // 生成类的二进制字节码
//                ClassWriter cw = new ClassWriter(0);
//                // 版本号
//                // 访问修饰符
//                // 类名
//                // 包名
//                // 父类
//                // 接口名称,这里没有实现接口
//                cw.visit(Opcodes.V1_8, Opcodes.ACC_PUBLIC, "Class" + i, null, "java/lang/Object", null);
//                // 返回byte数组
//                byte[] code = cw.toByteArray();
//                // 加载类,生成Class对象
//                test.defineClass("Class" + i, code, 0, code.length);
//            }
//        }finally {
//            System.out.println(j);
//        }
//    }
//
//}
