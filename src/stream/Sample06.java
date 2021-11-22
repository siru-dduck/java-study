package stream;

import java.util.Arrays;
import java.util.List;

public class Sample06 {

    public static void main(String[] args) {
        List<String> list = Arrays.asList("Toy", "Robot", "Box");
        list.stream()
                .map(String::length)
                .forEach(s -> System.out.print(s + "\t"));
        System.out.println();
    }
}
