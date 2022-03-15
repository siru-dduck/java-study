package optional;

import java.util.Optional;

public class Sample01 {
    public static void main(String[] args) {
        Optional<String> optNotNull = Optional.of("문자열"); // "문자열"을 담고있는 Optional 객체 생성
        Optional<String> optNull = Optional.empty(); // null을 담고있는 Optional 객체 생성
        
        System.out.println(optNotNull.get()); // 정상실행
        System.out.println(optNull.get()); // ❗ NoSuchElementException 예외발생
    }
}
