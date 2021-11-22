package stream;

import java.util.Arrays;

public class Sample03 {

    public static void main(String[] args) {
        String[] arr = {"KIM", "LEE", "CHOI"};
        Arrays.stream(arr)
                .forEach(System.out::println);
    }

}
