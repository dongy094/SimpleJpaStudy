package jpastudy.jpaboard.Service;

import jpastudy.jpaboard.Repository.MemberRepository;
import jpastudy.jpaboard.domain.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.TypedQuery;
import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    //가입하기
    @Transactional
    public Long join(Member member){
        validateMember(member); // 회원 중복 검증
        memberRepository.save(member);
        return member.getId();
    }

    public int validateMember(Member member) {
        List<Member> byName = memberRepository.findByName(member.getUserName());
        int check = 0;
        if(!byName.isEmpty()){
            check = 1;
            //throw new IllegalStateException("이미 존재하는 회원 이름 ");
        }
        return check;
    }

    //
    public Member findMember(Long memberId){
        return memberRepository.findOne(memberId);
    }

    public List<Member> findMembers(){
        return memberRepository.membersAll();
    }

    public Member signin(String user_name, Long user_password){
        return memberRepository.signinMember(user_name,user_password);
    }

}
