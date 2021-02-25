package concurrent;

import java.util.concurrent.*;

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
