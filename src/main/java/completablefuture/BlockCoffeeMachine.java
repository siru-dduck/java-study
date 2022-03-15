package completablefuture;

import java.util.HashMap;
import java.util.Map;

class BlockCoffeeMachine {
    private Map<String, Integer> menu;

    public BlockCoffeeMachine() {
        menu = new HashMap<>();
        menu.put("latte", 3000);
        menu.put("americano", 2000);
        menu.put("drip", 4500);
    }

    public Integer getPriceBy(String coffeeName) {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return menu.get(coffeeName);
    }
}
