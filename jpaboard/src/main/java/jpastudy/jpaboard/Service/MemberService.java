package jpastudy.jpaboard.Service;

import jpastudy.jpaboard.Dto.MemberForm;
import jpastudy.jpaboard.Repository.MemberRepository;
import jpastudy.jpaboard.domain.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    //가입하기
    @Transactional
    public Long join(Member member){
            memberRepository.save(member);
            return member.getId();

    }

    public void validateMember(Member member) {
        List<Member> members = memberRepository
                               .findByName(member.getUserName());
        int size = members.size();

        if(size>=1){
            throw new IllegalStateException("이미 존재하는 회원 이름 ");
        }

    }

    public Long findUserIdByName(String userName){
        List<Member> member = memberRepository.findByName(userName);
        return member.get(0).getId();
    }

    //
    public Member findMember(Long memberId){
        return memberRepository.findOne(memberId);
    }

    public List<Member> findMembers(){
        return memberRepository.membersAll();
    }

    public List<Member> signin(MemberForm memberForm){

        return memberRepository.signinMember(memberForm);

    }


}
