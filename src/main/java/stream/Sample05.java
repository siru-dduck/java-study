package stream;

import java.util.Arrays;
import java.util.List;

public class Sample05 {
    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 4, 5};
        Arrays.stream(arr)
                .filter(n -> n % 2 == 1) // 홀수만 통과
                .forEach(n -> System.out.print(n + "\t"));
        System.out.println();

        List<String> list = Arrays.asList("Toy", "Robot", "Box");
        list.stream()
                .filter(s -> s.length() >= 3) // 길이가 3이상인 문자열만 통과
                .forEach(s -> System.out.print(s + "\t"));
        System.out.println();
    }
}
