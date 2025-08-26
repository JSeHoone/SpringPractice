package com.sehoon.springbootPractice.service;

import static org.junit.jupiter.api.Assertions.*;

import com.sehoon.springbootPractice.domain.Member;
import com.sehoon.springbootPractice.repository.MemoryMemberRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class MemberServiceTest {

    MemberService memberService;
    MemoryMemberRepository memberRepository;

    @BeforeEach
    public void beforeEach() {
        memberRepository = new MemoryMemberRepository();
        memberService = new MemberService(memberRepository);
    }
    @AfterEach
    public void afterEach() {
        memberRepository.clearStore();
    }


    @Test
    void join() {
        // given : 먼가가 주어졌을 때,
        Member member = new Member();
        member.setName("hello");

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
        // assertThrows(예외 클래스, 람다식)
//        try {
//            memberService.join(member2);
//            fail("");
//        } catch (IllegalStateException e) {
//            Assertions.assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");
//        }


        //then
    }

    @Test
    void findMembers() {
    }

    @Test
    void findOne() {
    }
}