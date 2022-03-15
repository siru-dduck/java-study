package stream;

import java.util.Arrays;

public class Sample02 {

    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 4, 5};
        int sum = Arrays.stream(arr) // 배열 -> 스트림
                        .filter(n -> n % 2 == 1) // 중간연산 실행
                        .sum(); // 최종연산 실행
        System.out.println(sum);
    }
}
