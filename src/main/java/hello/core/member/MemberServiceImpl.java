package hello.core.member;

public class MemberServiceImpl implements MemberService{
    /**
     * MemberServiceImpl은 이제부터 '의존관계' 에 대한 고민은 '외부'(AppConfig)에 맡기고 실행에만 집중하면 된다.
     **/
    private final MemberRepository memberRepository;

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
}
