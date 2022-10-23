package edu.inflearn.intro;

import edu.inflearn.intro.repository.MemberRepository;
import edu.inflearn.intro.repository.MemoryMemberRepository;
import edu.inflearn.intro.service.MemberService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringConfig {
    @Bean
    public MemberService memberService() {
        return new MemberService(memberRepository());
    }

    @Bean
    public MemberRepository memberRepository() {
        return new MemoryMemberRepository();
    }
}
