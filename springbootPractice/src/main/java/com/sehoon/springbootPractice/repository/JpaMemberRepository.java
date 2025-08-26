package com.sehoon.springbootPractice.repository;

import com.sehoon.springbootPractice.domain.Member;
import jakarta.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

public class JpaMemberRepository implements MemberRepository{

    // JPA는 이 EntityManager로 모든게 동작한다.
    private final EntityManager em;

    public JpaMemberRepository(EntityManager em) {
        this.em = em;
    }

    @Override
    public Member save(Member member) {
        em.persist(member); // 이 코드가 DB에 데이터를 저장하고, member객체에 setId까지 다 해준다.
        return member;
    }

    @Override
    public Optional<Member> findById(Long id) {
        Member member = em.find(Member.class, id);
        return Optional.ofNullable(member);
    }

    @Override
    public Optional<Member> findByName(String name) {
        List<Member> result = em.createQuery("select m from Member m where m.name = :name", Member.class)
                .setParameter("name", name)
                .getResultList();

        return result.stream().findAny();
    }

    @Override
    public List<Member> findAll() {
        // JPQL이라는 특별한 객체지향 쿼리를 사용한다.
        // 객체(Entity)를 대상으로 쿼리를 날리는 것이다. -> 이것이 SQL로 번역이 된다.
        return em.createQuery("select m from Member m", Member.class)
                .getResultList();
    }
}
