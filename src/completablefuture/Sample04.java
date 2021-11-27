package completablefuture;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

public class Sample04 {

    public static void main(String[] args) {
        NonBlockCoffeeMachine coffeeMachine = new NonBlockCoffeeMachine();

        CompletableFuture<Integer> latteFuture = coffeeMachine.getPriceBy("latte"); // 결과가 완성되지 않아도 바로 제어권 반환
        CompletableFuture<Integer> americanoFuture = coffeeMachine.getPriceBy("americano"); // 결과가 완성되지 않아도 바로 제어권 반환
        CompletableFuture<Integer> dripFuture = coffeeMachine.getPriceBy("drip"); // 결과가 완성되지 않아도 바로 제어권 반환

        List<CompletableFuture<Integer>> coffeeList = Arrays.asList(latteFuture, americanoFuture, dripFuture);

        int totalPrice = CompletableFuture.allOf(latteFuture, americanoFuture, dripFuture)
                .thenApply(Void -> coffeeList.stream()
                        .map(CompletableFuture::join)
                        .collect(Collectors.toList()))
                .join()
                .stream()
                .mapToInt(i->i)
                .sum();

        System.out.println("총가격: " + totalPrice);
    }
}
