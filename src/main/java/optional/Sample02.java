package optional;

import java.util.Optional;

public class Sample02 {
    public static void main(String[] args) {
        Optional<String> opt = Optional.ofNullable("Optional 객체"); // of메소드와는 달리 null을 포함할 가능성이 있다.
        Optional<String> opt2 = Optional.empty();

        if(opt.isPresent()) {
            System.out.println(opt.get());
        }

        System.out.println(opt2.orElse("대체 문자열"));
    }
}
