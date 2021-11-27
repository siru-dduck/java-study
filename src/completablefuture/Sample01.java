package completablefuture;

import java.util.HashMap;
import java.util.Map;

public class Sample01 {
    public static void main(String[] args) {
        BlockCoffeeMachine coffeeMachine = new BlockCoffeeMachine();

        long startTime = System.currentTimeMillis();
        Integer lattePrice = coffeeMachine.getPriceBy("latte"); // 1초간 블록킹
        Integer americanoPrice = coffeeMachine.getPriceBy("americano"); // 1초간 블록킹
        Integer dripPrice = coffeeMachine.getPriceBy("drip"); // 1초간 블록킹
        System.out.printf("가격조회 시간: %d초\n", (System.currentTimeMillis() - startTime) / 1000);

        System.out.printf("라떼의 가격은 %d원 입니다.\n", lattePrice);
        System.out.printf("아메리카노의 가격은 %d원 입니다.\n", americanoPrice);
        System.out.printf("드립커피의 가격은 %d원 입니다.\n", dripPrice);
    }
}
