package generic;

class IntBox {
    private int value;

    public int get() {
        return value;
    }

    public void set(int value) {
        this.value = value;
    }

}

class StringBox {
    private String string;

    public String get() {
        return string;
    }

    public void set(String string) {
        this.string = string;
    }
}


public class Sample01 {
    public static void main(String[] args) {
        IntBox age = new IntBox();
        StringBox name = new StringBox();

        age.set(25);
        name.set("시루떡");

        int personAge = age.get();
        String personName = name.get();

        System.out.println(personName + "은 " + personAge + "살 입니다." );
    }
}

