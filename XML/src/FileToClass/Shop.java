package FileToClass;

import java.util.List;

public class Shop {
    public Goods goods;
    public int count;
    public double profit;
    public String[] secretData;

    Shop(){

    }
    static class Goods{
        public List<String> names;
    }
}
