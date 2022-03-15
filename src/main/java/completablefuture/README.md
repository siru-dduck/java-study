# CompletableFuture

## Blocking과 CompletableFuture의 필요성
```java
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

```
`BlockCoffeeMachine`은 주어진 커피이름에 따른 가격을 반환하는 `getPriceBy`메소드를 가지고 있다. 이 함수는 값을 반환하기까지 1초의 시간이
소요된다. 따라서 `main`메소드 입장에서 `getPriceBy`를 호출하면 결과를 반환받을때까지 아무일도 할 수 없는 Block상황이 된다.
`lattePrice`결과를 받기 전까지는 americano와 dripCoffee의 가격을 조회할 수 없다. 가격을 조회하는데 대략 3초가량 소요된다. 이는 매우 비효율적이다. 이 문제를 해결하기위해
`CompletableFuture`가 등장하게 되었다.

## CompletableFuture의 원형
```java
public class CompletableFuture<T> implements Future<T>, CompletionStage<T> { }
```
위는 `CompletableFuture`의 클래스 원형이다. 비동기 작업의 결과를 처리하기위해 설계된 `Future`인터페이스를 상속받는다.

## CompletableFuture 기본사용법
```java
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

```
`getPriceBy`메소드는 결과를 `CompletableFuture`을 반환한다. 이때 데이터조회 작업을 다른 스레드에서 새로 비동기로 실행하게 된다.
`getPriceBy`메소드는 이전과 달리 결과가 나오지 않아도 바로 제어권 반환하기 때문에 Blocking이 되어 발생했던 비효율이 사라졌다. 데이터 조회
시간도 3초에서 1초로 줄었다. 위의 코드는 여전히 문제점이 있다. 다른 스레드를 생성해서 작업을 처리하는 코드는 깔끔하지 않고 `join`함수의
경우 `CompletableFuture`의 작업이 끝날때까지 Blocking 되므로 코드의 개선여지가 있다.

## CompletableFuture 개선
```java
class NonBlockCoffeeMachine {
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
        NonBlockCoffeeMachine coffeeMachine = new NonBlockCoffeeMachine();

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

        latteFuture.join(); americanoFuture.join(); dripFuture.join(); // 작업이 모두 끝날때까지 대기
    }
}
```

`getPriceBy`메소드는 `CompletableFuture`에서 제공하는 `supplyAsync`메소드를 이용해 코드를 간결하게 했다.
비동기 작업은 common fork/join thread pool에서 진행하게 된다. 그리고 비동기 작업이 완료되었을때 수행할 작업을 람다식으로 즉 콜백함수
형태로 작성할 수 있다. `thenAccept`메소드 내에 람다식을 선언해 비동기 작업의 결과를 가지고 후처리를 할 수 있다.

## 여러개의 CompletableFuture 다루기
```java
public class Sample04 {

    /**
     * 커피의 총가격 구하기 예제
     */
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

```
여러개의 `CompletableFuture`를 다루기 위해 `allOf`메소드를 사용할 수 있다. `allOf`메소드는 `CompletableFuture<Void>`타입을 반환하므로
`thenAccept`처럼 결과값을 인자로 받을 수 없다. `thenAccept`메소드 내부에서 `join`메소드를 이용해 결과값을 가져오는 등의 비동기작업을 처리해야한다.