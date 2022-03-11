# concurrent (동시성)

## Executor (스레드 풀)
### 기존의 스레드 생성방법과 한계
```java
Runnable task1 = () -> { ... }
Thread t1 = new Thread(task1);
t1.start();
```

위의 코드는 자바5이후의 concurrent 패키지가 나오기 이전의 스레드 생성방법이다. 이 방법은 스레드의 생성과 소멸과정에서 시스템에 부담을 줄 수 있다.
그래서 스레드 풀이라는 개념이 등장하게 되었다. 스레드 풀안에 미리 스레드를 생성해 이를 재활용할 수 있다. 자바에서는 스레드 풀을 구현하지 않고도
사용할 수 있도록 자바5이후부터 concurrent패키지를 제공한다.

### 간단한 Executor(스레드 풀)사용법
```java
public class Sample01 {
    public static void main(String[] args) {
        Runnable task = () -> {
            int n1 = 10;
            int n2 = 20;
            String name = Thread.currentThread().getName();
            System.out.println(name + ": " + (n1+n2));
        };

        ExecutorService exr = Executors.newSingleThreadExecutor();
        exr.submit(task);

        System.out.println("End " + Thread.currentThread().getName());
        exr.shutdown();
    }
}
```
`Executors.newSingleThreadExecutor`를 호출해 스레드 풀을 생성하고 `exr.submit`을 호출해 스레드풀에 작업을 전달하고 스레드 풀안의 스레드가
작업을 처리한다.
`Excutors`클래스에서 여러 유형의 스레드 풀을 생성할 수 있다.
* `newSingleThreadExecutor`: 풀 안에 하나의 스레드만 생성하고 유지한다.
* `newFixedThreadPool`: 풀 안에 인자로 전달된 수의 스레드를 생성하고 유지한다.
* `newCachedThreadPool`: 풀 안의 스레드의 수를 작업의 수에 맞게 유동적으로 관리한다.

## Callable & Future
`Runnable`인터페이스는 반환형이 `void`이기 때문에 작업의 결과를 리턴할 수 없다. 그래서 `Callable`인터페이스와 `Future`인터페이스가 등장했다.
```java
public class Sample02 {
    public static void main(String[] args) throws InterruptedException, ExecutionException {
        Callable<Integer> task = () -> {
            int sum = 0;
            for (int i = 0; i < 10; i++) {
                sum += i;
            }
            return sum;
        };
        ExecutorService exr = Executors.newSingleThreadExecutor();
        Future<Integer> future = exr.submit(task);

        Integer result = future.get();
        System.out.println("결과: " + result);
        exr.shutdown();
    }
}
```
`Callable`람다식 작성시 작업의 결과에 대한 타입을 명시후 람다식을 작성하고 `Runnable`인터페이스 작업을 넘겼던거처럼 `submit`함수를 호출해 작업을 넘기고
`Future`타입으로 반환값을 받는다. `future.get`을 통해 스레드가 반환한 값을 얻어올 수 있다.