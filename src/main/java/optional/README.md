# Optional 클래스

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