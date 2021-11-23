package hello.core.discount;

import hello.core.member.Grade;
import hello.core.member.Member;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Component
@Primary // 조회 대상 빈이 2개 이상일때 Primary가 붙은 클래스를 우선순위로 두어 의존관계 주입
//@Qualifier("mainDiscountPolicy") // 조회 대상 빈이 2개 이상일때 Qualifier을 이용해 구분 -> OrderServiceImpl 참조
public class RateDiscountPolicy implements DiscountPolicy{

    private int discountPercent = 10;

    /**
     * 할인 금액
     * */
    @Override
    public int discount(Member member, int price) {

        if (member.getGrade() == Grade.VIP){
            return price * discountPercent / 100;
        } else{
            return 0;
        }
    }
}
