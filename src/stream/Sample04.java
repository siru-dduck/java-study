package stream;

import java.util.Arrays;
import java.util.List;

public class Sample04 {
    public static void main(String[] args) {
        List<String> list = Arrays.asList("Toy", "Robot", "Box");
        list.stream()
                .forEach(System.out::println);
    }
}
