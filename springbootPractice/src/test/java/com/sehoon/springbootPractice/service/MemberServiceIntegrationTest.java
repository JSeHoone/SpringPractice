package com.sehoon.springbootPractice.service;

import static org.junit.jupiter.api.Assertions.assertThrows;

import com.sehoon.springbootPractice.domain.Member;
import com.sehoon.springbootPractice.repository.MemberRepository;
import com.sehoon.springbootPractice.repository.MemoryMemberRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Transactional
class MemberServiceIntegrationTest {

    @Autowired MemberService memberService;
    @Autowired MemberRepository memberRepository;


    @Test
    void join() {
        // given : 먼가가 주어졌을 때,
        Member member = new Member();
        member.setName("spring");

        // when : 이것을 수행했을 때,
        long savedId = memberService.join(member);

        // then : 이것이 나와야 해
        Member findedMember = memberService.findOne(savedId).get();
        Assertions.assertThat(member.getName()).isEqualTo(findedMember.getName());
    }

    @Test
    public void 중복_회원_예외() {
        // given
        Member member1 = new Member();
        member1.setName("spring");

        Member member2 = new Member();
        member2.setName("spring");

        // when
        memberService.join(member1);
        IllegalStateException illegalStateException = assertThrows(IllegalStateException.class,
                () -> memberService.join(member2));

        Assertions.assertThat(illegalStateException.getMessage()).isEqualTo("이미 존재하는 회원입니다.");


        //then
    }

}