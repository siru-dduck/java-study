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
        new Thread(()->{
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

public class Sample02 {
    public static void main(String[] args) {
        NonBlockCoffeeMachine coffeeMachine = new NonBlockCoffeeMachine();

        long startTime = System.currentTimeMillis();
        CompletableFuture<Integer> latteFuture = coffeeMachine.getPriceBy("latte"); // 결과가 완성되지 않아도 바로 제어권 반환
        CompletableFuture<Integer> americanoFuture = coffeeMachine.getPriceBy("americano"); // 결과가 완성되지 않아도 바로 제어권 반환
        CompletableFuture<Integer> dripFuture = coffeeMachine.getPriceBy("drip"); // 결과가 완성되지 않아도 바로 제어권 반환

        Integer lattePrice = latteFuture.join();
        Integer americanoPrice = americanoFuture.join();
        Integer dripPrice = dripFuture.join();
        System.out.printf("가격조회 시간: %d초\n", (System.currentTimeMillis() - startTime) / 1000);

        System.out.printf("라떼의 가격은 %d원 입니다.\n", lattePrice);
        System.out.printf("아메리카노의 가격은 %d원 입니다.\n", americanoPrice);
        System.out.printf("드립커피의 가격은 %d원 입니다.\n", dripPrice);
    }
}
