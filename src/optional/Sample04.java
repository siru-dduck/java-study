package optional;

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
