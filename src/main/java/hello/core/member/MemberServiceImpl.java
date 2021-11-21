package hello.core.member;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MemberServiceImpl implements MemberService{
    /**
     * MemberServiceImpl은 이제부터 '의존관계' 에 대한 고민은 '외부'(AppConfig)에 맡기고 실행에만 집중하면 된다.
     **/
    private final MemberRepository memberRepository;

    @Autowired //의존관계를 자동으로 주입해준다
    public MemberServiceImpl(MemberRepository memberRepository){
        this.memberRepository = memberRepository;
    }

    @Override
    public void join(Member member) {
        memberRepository.save(member);
    }

    @Override
    public Member findMember(Long MemberId) {
        return memberRepository.findById(MemberId);
    }

    //테스트 용도
    public MemberRepository getMemberRepository() {
        return memberRepository;
    }
}
