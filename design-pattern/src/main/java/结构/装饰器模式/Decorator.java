package 结构.装饰器模式;

public class Decorator implements MilkTea {

    protected MilkTea milkTea;

    public Decorator(MilkTea MilkTea) {
        this.milkTea = MilkTea;
    }

    @Override
    public String getDescription() {
        return milkTea.getDescription();
    }

    @Override
    public double getPrice() {
        return milkTea.getPrice();
    }
}
