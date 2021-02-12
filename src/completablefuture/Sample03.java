package completablefuture;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

class NonBlockCoffeeMachine2 {
    private Map<String, Integer> menu;

    public NonBlockCoffeeMachine2() {
        menu = new HashMap<>();
        menu.put("latte", 3000);
        menu.put("americano", 2000);
        menu.put("drip", 4500);
    }

    public CompletableFuture<Integer> getPriceBy(String coffeeName) {
        return CompletableFuture.supplyAsync(() -> {
            System.out.println("다른 스레드에서 작업시작");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return menu.get(coffeeName);
        });
    }
}

public class Sample03 {
    public static void main(String[] args) {
        NonBlockCoffeeMachine2 coffeeMachine = new NonBlockCoffeeMachine2();

        CompletableFuture<Integer> latteFuture = coffeeMachine.getPriceBy("latte"); // 결과가 완성되지 않아도 바로 제어권 반환
        CompletableFuture<Integer> americanoFuture = coffeeMachine.getPriceBy("americano"); // 결과가 완성되지 않아도 바로 제어권 반환
        CompletableFuture<Integer> dripFuture = coffeeMachine.getPriceBy("drip"); // 결과가 완성되지 않아도 바로 제어권 반환

        latteFuture.thenAccept((price) -> {
            System.out.printf("라떼의 가격은 %d원 입니다.\n", price);
        });
        americanoFuture.thenAccept((price) -> {
            System.out.printf("아메리카노의 가격은 %d원 입니다.\n", price);
        });
        dripFuture.thenAccept((price) -> {
            System.out.printf("드립커피의 가격은 %d원 입니다.\n", price);
        });

        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}