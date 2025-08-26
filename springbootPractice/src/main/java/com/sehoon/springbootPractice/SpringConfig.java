package com.sehoon.springbootPractice;

import com.sehoon.springbootPractice.repository.JdbcMemberRepository;
import com.sehoon.springbootPractice.repository.MemberRepository;
import com.sehoon.springbootPractice.repository.MemoryMemberRepository;
import com.sehoon.springbootPractice.service.MemberService;
import javax.sql.DataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringConfig {
    private final DataSource dataSource;

    public SpringConfig(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Bean
    public MemberService memberService() {
        return new MemberService(memberRepositorty());
    }

    @Bean
    public MemberRepository memberRepositorty() {
//        return new MemoryMemberRepository();
        return new JdbcMemberRepository(dataSource);
    }
}
