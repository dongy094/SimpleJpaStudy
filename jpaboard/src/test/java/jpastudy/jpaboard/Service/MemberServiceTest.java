package jpastudy.jpaboard.Service;

import jpastudy.jpaboard.Repository.MemberRepository;
import jpastudy.jpaboard.domain.Comment;
import jpastudy.jpaboard.domain.Member;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.TypedQuery;

import static org.junit.Assert.*;


@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class MemberServiceTest {

    @Autowired MemberService memberService;
    @Autowired MemberRepository memberRepository;


    @Test
    public void 회원가입() throws Exception{
        //given
        Member member = new Member();
        member.setUserName("UserA");

        //when
        Long memberId = memberService.join(member);

        //then
        Assertions.assertThat(member).isEqualTo(memberRepository.findOne(memberId));

    }

    
    @Test
    public void 로그인() throws Exception{
        //given
        Member member = new Member();
        member.setUserName("UserA");
        member.setPassword(1234L);
        Long memberId = memberService.join(member);
        //when

        Member userA = memberService.signin("UserA", 1234L);
        System.out.println("=========");
        System.out.println("userA.getUserName() = " + userA.getUserName());
        System.out.println("=========");

        //then
    
    }
    
    
    @Test
    public void 코멘트() throws Exception{
        //given
        Member member = new Member();
        member.setUserName("UserA");
        member.setPassword(1234L);
        Long memberId = memberService.join(member);
        //when

        //then
    
    }
    
}