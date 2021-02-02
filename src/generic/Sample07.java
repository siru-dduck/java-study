package generic;

class Data {
    String value;

    public <T> void setValue(T value) {
        this.value = value.toString();
    }

    public String getValue() {
        return value;
    }
}

public class Sample07 {
    public static void main(String[] args) {
        Data data = new Data();
        data.setValue(3);
        System.out.println(data.getValue());
    }
}
