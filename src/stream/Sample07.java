package stream;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

class ToyPriceInfo {
    private String model;
    private int price;

    public ToyPriceInfo(String model, int price) {
        this.model = model;
        this.price = price;
    }

    public int getPrice() {
        return price;
    }

}

public class Sample07 {

    public static void main(String[] args) {
        List<ToyPriceInfo> list = Arrays.asList(
                new ToyPriceInfo("GUN_LS_23", 200),
                new ToyPriceInfo("TEDDY_TY_23", 450),
                new ToyPriceInfo("CAR_TRA_9212", 700));

        int sum = list.stream()
                .filter(t -> t.getPrice() < 500)
                .mapToInt(ToyPriceInfo::getPrice)
                .sum();
        System.out.println(sum);

    }
}
