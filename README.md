# Java Syntax Training
자바문법 연습

---
# 📑 공부일지
## 내부 클래스
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

[예제코드](https://github.com/sinwoo1225/java-syntax-training/tree/master/src/innerclass)

---

## 제네릭
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

### 여러개의 제네릭 타입``
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