package com.sehoon.springbootPractice;

import com.sehoon.springbootPractice.repository.MemberRepositorty;
import com.sehoon.springbootPractice.repository.MemoryMemberRepository;
import com.sehoon.springbootPractice.service.MemberService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringConfig {

    @Bean
    public MemberService memberService() {
        return new MemberService(memberRepositorty());
    }

    @Bean
    public MemberRepositorty memberRepositorty() {
        return new MemoryMemberRepository();
    }
}
