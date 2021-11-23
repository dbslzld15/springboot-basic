package hello.core.order;

import hello.core.annotation.MainDiscountPolicy;
import hello.core.discount.DiscountPolicy;
import hello.core.member.Member;
import hello.core.member.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
//@RequiredArgsConstructor // final 이 붙은 arguments 들로 생성자를 자동 생성
public class OrderServiceImpl implements OrderService{

    /**
     * 구현체를 직접 생성하는 방법은 DIP를 위반하기때문에 인터페이스에만 의존할 수 있도록 수정
     * 구체적인 클래스에 대해서 몰라야 함
     * 해당 클래스는 DiscountPolicy에 대해 FixDiscount가 들어올지, RateDiscount가 들어올지 몰라야함
     * private final DiscountPolicy discountPolicy = new FixDiscountPolicy(); 보다는
     * private final DiscountPolicy discountPolicy; 로 가져간 뒤 AppConfig에서 생성자 주입을 통해 설정
     * */
    private final MemberRepository memberRepository;
    private final DiscountPolicy discountPolicy;

    /*
    @Autowired
    public OrderServiceImpl(MemberRepository memberRepository,
                            @Qualifier("mainDiscountPolicy") DiscountPolicy discountPolicy) { //Qualifier이 붙은 빈 사용
        this.memberRepository = memberRepository; // @RequiredArgsConstructor가 생성자를 대신함
        this.discountPolicy = discountPolicy;
    }
    */

    @Autowired
    public OrderServiceImpl(MemberRepository memberRepository, @MainDiscountPolicy DiscountPolicy discountPolicy) {
        this.memberRepository = memberRepository;
        this.discountPolicy = discountPolicy;
    }

    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {
        Member member = memberRepository.findById(memberId);
        int discountPrice = discountPolicy.discount(member, itemPrice);

        return new Order(memberId, itemName, itemPrice, discountPrice);
    }

    //테스트 용도
    public MemberRepository getMemberRepository() {
        return memberRepository;
    }
}
