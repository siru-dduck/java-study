package stream;

import java.util.Arrays;
import java.util.stream.IntStream;

public class Sample01 {

    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 4, 5};
        IntStream stm1 = Arrays.stream(arr); // 배열 -> 스트림
        IntStream stm2 = stm1.filter(n -> n % 2 == 1); // 중간연산 실행
        int sum = stm2.sum(); // 최종연산 실행
        System.out.println(sum);
    }

}
