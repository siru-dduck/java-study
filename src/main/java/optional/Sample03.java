package optional;

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
