package com.example.inflearnweb.service;

import com.example.inflearnweb.domain.Member;
import com.example.inflearnweb.repository.MemberRepository;
import com.example.inflearnweb.repository.MemoryMemberRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.assertThrows;


@SpringBootTest //스프링 컨테이너와 테스트를 함께 한다.(순수 자바 코드가 아닌 스프링으로 테스트하는 것) 하지만 순수한 단위 테스트가 훨씬 더 좋음!
@Transactional // AfterEach로 clear 해주는 과정 없애도 되게 해줌. -> TEST 시작 전에 트랜잭션을 시작하고 끝난 뒤에는 DB에 Insert한 값들을 롤백해줌(대박)
class MemberServiceIntegrationTest {

    // 테스트 할 때는 편의를 위해 필드 주입 써도 된다.
    @Autowired
    MemberService memberService;
    @Autowired
    MemberRepository memberRepository;



    @Test //한글로 메서드 적어도 무방
//    @Commit //테스트지만 db에 반영
    void 회원가입() {
        // given
        Member member = new Member();
        member.setName("spring");

        // when
        Long saveId = memberService.join(member);

        // then
        Member findMember = memberService.findOne(saveId).get();
        Assertions.assertThat(member.getName()).isEqualTo(findMember.getName());
    }

    @Test
    public void 중복_회원_예외(){
        // given
        Member member1 = new Member();
        member1.setName("spring");

        Member member2 = new Member();
        member2.setName("spring");

        // when
        memberService.join(member1);
        IllegalStateException e = assertThrows(IllegalStateException.class, () -> memberService.join(member2));

        Assertions.assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");

        // 중복 처리 방법1 -> 좋지 않음
//        try {
//            memberService.join(member2);
//            fail();
//        }catch (IllegalStateException e){
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