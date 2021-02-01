package generic;

class ObjectBox {
    private Object object;

    public Object get() {
        return object;
    }

    public void set(Object object) {
        this.object = object;
    }

}

public class Sample02 {
    public static void main(String[] args) {
        ObjectBox age = new ObjectBox();
        ObjectBox name = new ObjectBox();

        age.set(25);
        name.set("시루떡");

         String personName = (String) name.get(); // ❗ 명시적으로 타입캐스팅을 해야한다.
        int personAge = (int) age.get();          // ❗ 명시적으로 타입캐스팅을 해야한다.

        System.out.println(personName + "은 " + personAge + "살 입니다." );
    }
}
