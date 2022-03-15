package innerclass;

// 함수형 인터페이스의 경우 아래 인터페이스를 통해 온전한 함수형 인터페이스임을 보장하고 명시할 수 있다.
// 여러개의 추상매소드를 인터페이스에 선언하면 컴파일 에러를 발생시킨다
@FunctionalInterface
interface Greeting {
    String sayHello(String name);
}

public class Sample04 {
    public static void main(String[] args) {
        Greeting greeting = new Greeting() {
            @Override
            public String sayHello(String name) {
                return name + "님 안녕하세요~!";
            }
        };

        System.out.println(greeting.sayHello("시루떡"));
    }
}