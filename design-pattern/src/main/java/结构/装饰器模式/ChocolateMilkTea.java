package 结构.装饰器模式;

public class ChocolateMilkTea implements MilkTea {

    private String description = "巧克力奶茶";

    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public double getPrice() {
        return 15;
    }
}
