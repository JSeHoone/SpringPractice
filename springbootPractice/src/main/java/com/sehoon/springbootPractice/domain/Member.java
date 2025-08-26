package com.sehoon.springbootPractice.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity // 해당 어노테이션을 넣으면 이제 이거는 JPA가 관리하는 엔티티이다.
public class Member {

    @Id // 이건 PK를 지정해주는 것
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 이건 자동 생성을 도와주는것(1씩 증가하는 전략 = IDENTITY)
    private Long id;

    @Column(name = "name") // DB에 정의된 테이블 컬럼 네임이랑 맵핑되는 것이다.
    private String name;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
