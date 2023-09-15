package 结构.装饰器模式;

public class Main {
    public static void main(String[] args) {
        // 选择巧克力奶茶
        MilkTea milkTea = new ChocolateMilkTea();
        // 第一次修饰，为巧克力奶茶添加布丁
        milkTea= new Pudding(milkTea);
        // 第二次修饰，为巧克力奶茶添加椰果
        milkTea = new Coconut(milkTea);
        System.out.println(milkTea.getDescription() + "\n加了布丁的巧克力奶茶的价格：" + milkTea.getPrice());

    }
}
