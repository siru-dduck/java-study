package stream;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class Sample12 {

    public static void main(String[] args) {
        List<String> list = Stream.of("Hello", "Box", "Robot", "Toy")
                .parallel() // 병렬 스트림 사용
                .filter(s -> s.length() < 5)
                .collect(ArrayList::new,
                        ArrayList::add,
                        ArrayList::addAll); // collect 내부 인자로 Collectors.toList() 사용해도 무방

        System.out.println(list);
    }
}
