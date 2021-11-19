package hello.core.beanfind;

import hello.core.member.MemberRepository;
import hello.core.member.MemoryMemberRepository;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

public class ApplicationContextSameBeanFindTest { // 스프링 빈 조회 - 동일한 타입이 둘 이상
    
    AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(SameBeanConfig.class);
    
    @Configuration
    static class SameBeanConfig {
        
        @Bean
        public MemberRepository memberRepository1() {
            return new MemoryMemberRepository();
        }
        
        @Bean
        public MemberRepository memberRepository2() {
            return new MemoryMemberRepository();
        }
        
    }
}
