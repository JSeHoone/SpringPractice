package com.sehoon.springbootPractice.service;

import com.sehoon.springbootPractice.domain.Member;
import com.sehoon.springbootPractice.repository.MemberRepositorty;
import com.sehoon.springbootPractice.repository.MemoryMemberRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

// 서비스쪽은 비즈니스 용어를 쓰는 것이 좋다.
// repository는 개발적으로 용어를 적음.
@Service
public class MemberService {

    private final MemberRepositorty memberRepository;

    // 외부에서 넣어주도록 바꿔준다.
    // memberService입장에서는 repository를 외부에서 넣어준다.
    // 이것을 Dependency Injection (DI)라고 한다.
    @Autowired
    public MemberService(MemberRepositorty memberRepository) {
        this.memberRepository = memberRepository;
    }


    /**
     * 회원가입
     */
    public long join(Member member) {
        // 같은 이름이 있는 중복 회원은 안된다.(조건)

        validateDuplicatedMember(member); // 중복회원 검즘

        memberRepository.save(member);
        return member.getId();
    }

    private void validateDuplicatedMember(Member member) {
        memberRepository.findByName(member.getName())
                .ifPresent(m -> {
                    throw new IllegalStateException("이미 존재하는 회원입니다.");
                });
    }

    /**
     * 전체회원 조회
     */
    public List<Member> findMembers() {
        return memberRepository.findAll();
    }

    public Optional<Member> findOne(Long memberId) {
        return memberRepository.findById(memberId);
    }
}
