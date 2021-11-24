package stream;

import java.util.stream.Stream;

public class Sample11 {

    public static void main(String[] args) {
        Stream.of("Box", "Apple", "Robot")
                .sorted()
                .forEach(s -> System.out.println(s + "\t"));
        System.out.println();

        Stream.of("Box", "Apple", "Rabbit")
                .sorted((s1, s2) -> s1.length() - s2.length())
                .forEach(s -> System.out.println(s + "\t"));
        System.out.println();
    }
}
