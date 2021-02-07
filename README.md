# Java Syntax Training
자바문법 연습

---
# 📑 공부일지
## 내부 클래스 
[예제코드](https://github.com/sinwoo1225/java-syntax-training/tree/master/src/innerclass)
> **클래스 내부에 선언된 클래스**, 클래스에 다른 클래스를 선언하는 이유는 두 클래스가 서로 밀접한 관계에 있기 때문이다. 
> [출처](https://doublesprogramming.tistory.com/158)

### 내부 클래스의 종류
* 인스턴스 내부 클래스(inner class)
* 정적 중첩 클래스(static inner class)
* 익명 내부 클래스(anonymous class) +함수형 인터페이스, 람다
* 지역 내부 클래스(local inner class)
### 장점 
1. 서로 연관성있는 클래스를 내부에 포함시킴으로써 내부 클래스에서 외부로 접근할 수 있다.
2. 연관된 클래스가 내부에 있기 때문에, 읽기 쉬우며 내부코드의 복잡성을 줄일 수 있다.

---

## 제네릭
[예제코드](https://github.com/sinwoo1225/java-syntax-training/tree/master/src/generic)
### 제네릭의 필요성
아래의 코드와 같이 `int`, `String`타입을 각각 가지고있는 `IntBox`, `StringBox` 클래스를 만들었다.
```java
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
```
두 클래스 각각 가지고는 데이터 타입은 다르지만 모두 데이터를 수정, 접근하는 기능을 공통적으로 가지고 있다. 
따라서 내부의 속성의 타입을 `Object`로 바꾸면 아래와 같이 하나의 클래스로 작성할 수 있다.
```java
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
        int personAge = (int) age.get();         // ❗ 명시적으로 타입캐스팅을 해야한다.

        System.out.println(personName + "은 " + personAge + "살 입니다." );
    }
}
```
위의 코드의 문제점은 데이터를 꺼내올때 타입캐스팅을 명시적으로 해줘야 한다. 이는 개발의 번거러움을 초래할 수 있고
자칫 잘못하면 컴파일 시간이 아닌 실행중에 예외가 발생할 수 있다. 이때 제네릭을 사용하면 번거로움이 사라진다.
```java
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
```
### 제네릭 타입 제한하기
제네릭의 데이터 타입을 제한할 수 있다. 특정 객체를 상속하는 객체만 타입을 가지도록 `extends`키워드를 이용해 제한 할 수 있다. 
```java
// 컴퓨터 내장 부품 대상 객체
class ComputerPart { }

// 그래픽 카드
class GraphicCard extends ComputerPart { }

// 메모리
class Memory extends ComputerPart { }

// 모니터 (컴퓨터의 내장 부품은 아님)
class Monitor { }

class Computer<T extends ComputerPart> {
    private T component;

    public void toInfo() {
        System.out.println(component.getClass().getName());
    }

    public Computer(T component) {
        this.component = component;
    }

    public Computer() { }

    public void setComponent(T component) {
        this.component = component;
    }
}

public class Sample04 {
    public static void main(String[] args) {
        Computer<ComputerPart> vga = new Computer<>();
        vga.setComponent(new GraphicCard());
//        vga.setComponent(new Monitor()); // ❗ 컴파일에러 -> 타입호환에러

        Computer<ComputerPart> mem = new Computer<>(new Memory());
//        Computer<ComputerPart> mo = new Computer<>(new Monitor()); // ❗ 컴파일에러 -> 타입추론에러
        vga.toInfo();
        mem.toInfo();
    }
}

```
위의 `Computer`클래스가 가지고 있는 제네릭 타입 선언부에 `extends`키워드를 써서 `ComputerPart`
클래스를 상속하는 객체만 가질 수 있도록 제네릭 타입을 제한했다. 위의 예제코드에서 `ComputerPart`
를 포함하는'`Computer`객체에는 `Monitor`타입의 인스턴스를 넣을 수 없다. 왜냐하면 
`Monitor`클래스는 `ComputerPart`를 상속받지 않았기 때문에 타입제한이 걸렸기 때문이다.

### 생성자에서의 제네릭
```java
package generic;

class NewComputer {
    private ComputerPart computerPart;

    public <T extends ComputerPart> NewComputer(T computerPart){
        this.computerPart = computerPart;
    }

    public void toInfo() {
        System.out.println(computerPart.getClass().getName());
    }
}

public class Sample05 {
    public static void main(String[] args) {
        NewComputer mem = new NewComputer(new Memory());
//        NewComputer momitor = new NewComputer(new Monitor()); // ❗ 컴파일에러 -> 타입호환예외
        mem.toInfo();
    }
}
```
`class`키워드 옆에 제네릭 타입을 선언하지 않고 생성자에 제네리타입을 명시하는 방법도 있다.

### 여러개의 제네릭 타입
```java
package generic;

class Student<G extends Integer, C extends Integer> {
    G g;
    C c;

    public Student(G g, C c) {
        this.g = g;
        this.c = c;
    }

    public void printInfo(String name) {
        System.out.printf("%s(은)는 %d학년 %d반입니다.\n", name, g, c);
    }
}

public class Sample06 {
    public static void main(String[] args) {
        Student<Integer, Integer> younghee = new Student<>(1, 4);
        younghee.printInfo("영희");
    }
}

```
위처럼 제네릭 타입을 선언할때 `,`를  써서 여러개의 제네릭 타입을 선언할 수 있다.

### 제네릭 메소드
```java
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
```
메소드도 제네릭 타입 선언이 가능하다. 

`[접근제어자] (static) <T> [반환자료형] [메소드명] (T t)`의 형식으로 사용이 가능하다. 물론 제네릭 타입도 타입제한을 둘 수 있다.
```java
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
```
`setNumber`함수는 매개변수로`Number`클래스를 상속받는 객체만 상속받는다.
따라서 매개변수에 `String`타입의 객체를 넘기면 컴파일 에러가 발생한다.`

### 와일드카드

```java
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
```
위의 예제처럼 제네릭 메소드를 선언할때 매개변수에 제네릭 타입 대신 와일드카드를 사용할 수 있다. 
```java
class WrapperUtil {
    public static <T extends Number> void peekWithGenericType(Wrapper<T> wrapper) {
        System.out.println(wrapper.get());
    } // 제네릭 메소드 정의

    public static void peekWithWildCard(Wrapper<? extends Number> wrapper) {
        System.out.println(wrapper.get());
    } // 와일드카드 기반 메소드 정의
}
```
와일드카드 문법은 반환형 옆에 `<T>`와 같이 제네릭 타입을 선언하지 않아도 된다.
대신 매개변수 인자타입에 제네릭 타입대신 `?`를 쓴다. 와알트카드도 타입제한이 가능하다.
와일드카드 기반 문법이 좀더 간결해보인다.

---

## Optional 클래스
[예제코드](https://github.com/sinwoo1225/java-syntax-training/tree/master/src/optional)

`Optional<T>` 클래스는 Integer나 Double 클래스처럼 'T'타입의 객체를 포장해 주는 래퍼 클래스(Wrapper class)다.
따라서 Optional 인스턴스는 모든 객체타입의 참조 변수를 저장할 수 있습니다.

### Optional 클래스를 사용하는 이유
`Optional`클래스를 사용하게 되면 `NullPointerException`를 예외를 처리할 수 있는 방법을 제공받을 수 있다.
객체를 참조하는 참조변수에 대해 `null`을 검사하는 조건문없이 `Optional`클래스가 제공하는 메소드로 예외를 처리할 수 있다.

```java
import java.util.Optional;

public class Sample01 {
    public static void main(String[] args) {
        Optional<String> optNotNull = Optional.of("문자열"); // "문자열"을 담고있는 Optional 객체 생성
        Optional<String> optNull = Optional.empty(); // null을 담고있는 Optional 객체 생성
        
        System.out.println(optNotNull.get()); // 정상실행
        System.out.println(optNull.get()); // ❗ NoSuchElementException 예외발생
    }
}
```
`Optional`클래스가 제공하는 메소드를 알아보자. `of`메소드는 매개변수를 저장하는 `Optional`객체를 반환한다.
`empty`메소드는 `null`값을 가진 `Optional`객체를 반환한다. 만약 `of`메소드에 매개변수로 `null`을 지정하면 `NullPointerExceptiopn`
이 발생한다. 

```java
import java.util.Optional;

public class Sample02 {
    public static void main(String[] args) {
        Optional<String> opt = Optional.ofNullable("Optional 객체"); // of메소드와는 달리 null을 포함할 가능성이 있다.

        if(opt.isPresent()) {
            System.out.println(opt.get());
        }
    }
}

```
`Optioanl`객체는 `get`메소드를 통해 값을 읽어올 수 있다. 하지만 `null`을 가지고 있을때는 `NoSuchElementException`을 발생시킨다.
`get`메소드를 호출하기 전에는 `isPresent`메소드를 통해 `null`값을 체크할 수 있다.
`orElse`메소드를 통해 `Optional`객체가 `null`을 가지고 있을때 대체할 객체를 지정할 수 있다.

### 효과적인 NullPointerException 처리
`if`문을 통해 `null`값에 대한 예외처리는 코드의 가독성을 떨어뜨릴 수 있다.
`Optional`클래스를 통해서 예외처리 코드에 대한 가독성을 향상시킬 수 있다.

```java
import java.util.ArrayList;
import java.util.List;

class NotFoundMemberException extends RuntimeException {
    public NotFoundMemberException(String message) {
        super(message);
    }
}

class Member{
    private String name;
    private int age;

    public Member(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

}

class MemberManager {
    private static List<Member> memberList;
    static {
        memberList = new ArrayList<>();
        memberList.add(new Member("철수", 23));
        memberList.add(new Member("영희", 25));
        memberList.add(new Member("영식", 27));
    }

    static Member findBy(String name) {
        Member member = null;
        for(Member m : memberList) {
            if (m.getName().equals(name)) {
                member = m;
                break;
            }
        }
        return member;
    }
}

public class Sample03 {

    public static void main(String[] args) {
        Member cheolsu = MemberManager.findBy("철수");
        Member siroo = MemberManager.findBy("시루");

        if(cheolsu != null) {
            System.out.println(cheolsu.getName() +"의 나이는 " + cheolsu.getAge() + "살");
        } else {
            throw new NotFoundMemberException("멤버를 찾지 못했습니다.");
        }

        if(siroo != null) {
            System.out.println(siroo.getName() +"의 나이는 " + siroo.getAge() + "살");
        } else {
            throw new NotFoundMemberException("멤버를 찾지 못했습니다.");
        }
    }
}


```
위의 코드는  `Optional`클래스가 등장하기 이전의 전통적인 `null`처리방법이다.
만약 `MemberManager`를 통해 검색한 멤버가 없는 경우 예외를 던지고 검색한 멤버가 있는 경우 멤버정보를 출력하는  코드이다.
```java
import java.util.Optional;

public class Sample04 {
    public static void main(String[] args) {
        Member cheolsu = Optional.ofNullable(MemberManager.findBy("철수"))
                .orElseThrow(()->new NotFoundMemberException("멤버를 찾을 수 없습니다."));
        Member siroo = Optional.ofNullable(MemberManager.findBy("시루"))
                .orElseThrow(()->new NotFoundMemberException("멤버를 찾을 수 없습니다."));

        System.out.println(cheolsu.getName() +"의 나이는 " + cheolsu.getAge() + "살");
        System.out.println(siroo.getName() +"의 나이는 " + siroo.getAge() + "살");
    }
}

```
`ofElseThrow`메소드를 통해 `Optional`객체가 가지고 있는 값이 `null`일 경우 예외를 발생시킨다.
`Optional`객체를 이용하면 보다더 간결하게 `null`에 대한 예외를 처리할 수 있다.