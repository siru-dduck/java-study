package generic;

class NumberBox {
    public static  <Z extends Number> void setNumber(Z z) {
        System.out.printf("입력된 값은 [%s]입니다.\n", z.toString());
    }
}
public class Sample08 {
    public static void main(String[] args) {
        NumberBox.setNumber(20002);
        NumberBox.setNumber(20002L);
        NumberBox.setNumber(123.4);
//        NumberBox.setNumber("문자열"); // ❗ 컴파일 에러
//        NumberBox.setNumber("문자열은 에러!"); // ❗ 컴파일 에러
    }
}
