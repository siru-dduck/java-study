package innerclass;

public class Sample02 {

    void localMethod() {
        int age = 26;

        class LocalClass {
            public void howOldAreYou() {
                System.out.println("age is " + age);
            }
        }

        LocalClass innerClass = new LocalClass();
        innerClass.howOldAreYou();
    }

    void localMethod2() {
        int age = 26;

        class LocalClass {
            public void howOldAreYou() {
                System.out.println("age is " + age);
            }
        }

        LocalClass innerClass = new LocalClass();
        innerClass.howOldAreYou();
    }

    public static void main(String[] args) {
        Sample02 outer = new Sample02();
        outer.localMethod();
    }
}
