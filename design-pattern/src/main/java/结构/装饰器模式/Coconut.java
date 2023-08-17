package 结构.装饰器模式;

public class Coconut extends Decorator {

    private String description = "加了椰果！";

    public Coconut(MilkTea milkTea) {
        super(milkTea);
    }

    @Override
    public String getDescription() {
        return milkTea.getDescription() + "\n" + description;
    }

    @Override
    public double getPrice() {
        return milkTea.getPrice() + 3;  // 3 表示椰果的价格
    }

}
