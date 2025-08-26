package com.sehoon.springbootPractice.repository;

import com.sehoon.springbootPractice.domain.Member;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SpringDataJpaMemberRepository extends JpaRepository<Member, Long>, MemberRepository {

    // JPQL : select m from Member m where m.name = ?
    // 여기서 리플렉션 기술을 녹여서 구현한거임
    @Override
    Optional<Member> findByName(String name);
}
