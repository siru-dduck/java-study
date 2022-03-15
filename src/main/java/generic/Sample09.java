package generic;

import java.nio.file.Watchable;

class Wrapper<T> {
    T t;

    public T get() {
        return t;
    }

    public void set(T t) {
        this.t = t;
    }
}

class WrapperUtil {
    public static <T extends Number> void peekWithGenericType(Wrapper<T> wrapper) {
        System.out.println(wrapper.get());
    }

    public static void peekWithWildCard(Wrapper<? extends Number> wrapper) {
        System.out.println(wrapper.get());
    }
}

public class Sample09 {

    public static void main(String[] args) {
        Wrapper<Integer> integerWrapper = new Wrapper<>();
        Wrapper<Float> floatWrapper = new Wrapper<>();
        Wrapper<String> stringWrapper = new Wrapper<>();

        integerWrapper.set(1);
        floatWrapper.set(1.2f);
        stringWrapper.set("문자열");

        WrapperUtil.peekWithGenericType(integerWrapper);
        WrapperUtil.peekWithGenericType(floatWrapper);
//        WrapperUtil.peekWithGenericType(stringWrapper); // ❗ 컴파일 에러

        WrapperUtil.peekWithWildCard(integerWrapper);
        WrapperUtil.peekWithWildCard(floatWrapper);
//        WrapperUtil.peekWithWildCard(stringWrapper); // ❗ 컴파일 에러
    }
}
