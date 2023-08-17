package 结构.装饰器模式;

public class Pudding extends Decorator {

    private String description = "加了布丁！";

    public Pudding(MilkTea milkTea) {
        super(milkTea);
    }

    @Override
    public String getDescription() {
        return milkTea.getDescription() + "\n" + description;
    }

    @Override
    public double getPrice() {
        return milkTea.getPrice() + 5;  // 5表示布丁的价格
    }
}
