package com.example.inflearnweb.service;

import com.example.inflearnweb.domain.Member;
import com.example.inflearnweb.repository.MemberRepository;
import com.example.inflearnweb.repository.MemoryMemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;


//@Service //Service는 Component를 가지고 있음
//@Component // 스프링 빈으로 자동 등록됨
@Transactional
public class MemberService {
    private final MemberRepository memberRepository;

    //    @Autowired
    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    /**
     * 회원가입
     */

    public Long join(Member member) {

//        long start = System.currentTimeMillis();


        // 시간 측정할거면 이런식으로 해야되는데 AOP 이용하면 안 써도 됨
//        try{
//            // 같은 이름이 있는 중복 회원 X
//            validateDuplicateMember(member);
//            memberRepository.save(member);
//            return member.getId();
//        }finally {
//            long finish = System.currentTimeMillis();
//            long timeMs = finish - start;
//            System.out.println("join = "+timeMs+"ms");
//        }
        validateDuplicateMember(member);
        memberRepository.save(member);
        return member.getId();

    }

    private void validateDuplicateMember(Member member) {
        memberRepository.findByName(member.getName())
                .ifPresent(m -> {
                    throw new IllegalStateException("이미 존재하는 회원입니다.");
                });
    }

    /**
     * 전체 회원 조회
     */
    public List<Member> findMembers() {
        return memberRepository.findAll();
    }

    public Optional<Member> findOne(Long memberId) {
        return memberRepository.findById(memberId);
    }
}
