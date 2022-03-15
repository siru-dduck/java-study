package innerclass;

public class Sample03 {
    public static void main(String[] args) {
        Runnable thread = () -> System.out.println("익명 내부 클래스 실행");
        // 위의 코드는 아래와 같이 익명 내부 클래스의 형태로 나타낼 수 있다.
        // 함수형 인터페이스의 경우 람다식으로 위와 같이 쓸 수 있다
//        Runnable thread1 = new Runnable() {
//            @Override
//            public void run() {
//                System.out.println("익명 내부 클래스 실행");
//            }
//        };
        thread.run();
    }
}
