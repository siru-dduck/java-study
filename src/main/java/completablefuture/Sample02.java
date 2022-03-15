package completablefuture;

import java.util.concurrent.CompletableFuture;

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
