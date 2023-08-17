package 结构.装饰器模式;

public class QQMilkTea implements MilkTea {

    private String description = "QQ奶茶";

    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public double getPrice() {
        return 10;
    }
}
