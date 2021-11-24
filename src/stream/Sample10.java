package stream;

import java.util.stream.Stream;

public class Sample10 {

    public static void main(String[] args) {
        Stream<String> stream = Stream.of("Hi,My","Is,Siroo,Dduck");
        Stream<String> stream2 = stream.flatMap(s -> Stream.of(s.split(",")));
        stream2.forEach(s -> System.out.println(s + "\t"));
    }

}
