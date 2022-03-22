package com.example.inflearnweb;

import com.example.inflearnweb.repository.MemberRepository;
import com.example.inflearnweb.repository.MemoryMemberRepository;
import com.example.inflearnweb.service.MemberService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringConfig {

    @Bean
    public MemberService memberService(){
        return new MemberService(memberRepository());
    }

    @Bean
    public MemberRepository memberRepository(){
        return new MemoryMemberRepository();
    }
}
