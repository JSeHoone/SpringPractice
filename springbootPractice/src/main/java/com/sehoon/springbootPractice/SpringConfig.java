package com.sehoon.springbootPractice;

import com.sehoon.springbootPractice.repository.JdbcMemberRepository;
import com.sehoon.springbootPractice.repository.JdbcTemplateMemberRepository;
import com.sehoon.springbootPractice.repository.JpaMemberRepository;
import com.sehoon.springbootPractice.repository.MemberRepository;
import com.sehoon.springbootPractice.repository.MemoryMemberRepository;
import com.sehoon.springbootPractice.service.MemberService;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringConfig {
//    private final DataSource dataSource;
//
//    public SpringConfig(DataSource dataSource) {
//        this.dataSource = dataSource;
//    }

    // 이 부분에 @PersistenceContext를 넣어줘도 된다.
//    private EntityManager em;
//
//    @Autowired
//    public SpringConfig(EntityManager em) {
//        this.em = em;
//    }

    // Spring Data JPA
    private final MemberRepository memberRepository;

    @Autowired
    public SpringConfig(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Bean
    public MemberService memberService() {
//        return new MemberService(memberRepositorty());
        // Spring Data JPA를 사용하면 주입 받은 memberRepository를 사용할 수 있다.
        return new MemberService(memberRepository);
    }

//    @Bean
//    public MemberRepository memberRepositorty() {
//        return new MemoryMemberRepository();
//        return new JdbcMemberRepository(dataSource);
//        return new JdbcTemplateMemberRepository(dataSource);
//        return new JpaMemberRepository(em);
//    }
}
