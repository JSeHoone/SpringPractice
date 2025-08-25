package com.sehoon.springbootPractice.repository;

import com.sehoon.springbootPractice.domain.Member;
import java.util.List;
import java.util.Optional;

public interface MemberRepositorty {
    Member save(Member member);
    Optional<Member> findById(Long id);
    Optional<Member> findByName(String name);
    List<Member> findAll();
}
