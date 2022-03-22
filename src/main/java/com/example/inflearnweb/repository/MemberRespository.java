package com.example.inflearnweb.repository;

import com.example.inflearnweb.domain.Member;

import java.util.List;
import java.util.Optional;

public interface MemberRespository {
    Member save(Member member);
    // null 처리
    Optional<Member> findById(Long id);
    Optional<Member> findByName(String name);
    List<Member> findAll();
}
