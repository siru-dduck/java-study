package generic;

class Box<T>{
    private T t;

    public T get() {
        return t;
    }

    public void set(T t) {
        this.t = t;
    }
}

public class Sample03 {
    public static void main(String[] args) {
        Box<Integer> age = new Box<>();
        Box<String> name = new Box<>();

        age.set(25);
        name.set("시루떡");

        int personAge =  age.get();        // ✅ 타입캐스팅이 필요가 없다
        String personName =  name.get();   // ✅ 타입캐스팅이 필요가 없다

        System.out.println(personName + "은 " + personAge + "살 입니다." );
    }
}
