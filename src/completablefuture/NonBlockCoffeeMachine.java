package completablefuture;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

class NonBlockCoffeeMachine {
    private Map<String, Integer> menu;

    public NonBlockCoffeeMachine() {
        menu = new HashMap<>();
        menu.put("latte", 3000);
        menu.put("americano", 2000);
        menu.put("drip", 4500);
    }

    public CompletableFuture<Integer> getPriceBy(String coffeeName) {
        CompletableFuture<Integer> future = new CompletableFuture<>();
        new Thread(() -> {
            System.out.println("새로운 스레드로 작업시작");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            Integer result = menu.get(coffeeName);
            future.complete(result);
        }).start();
        return future;
    }
}
