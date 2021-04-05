package jpastudy.jpaboard.Repository;

import jpastudy.jpaboard.Dto.BoardForm;
import jpastudy.jpaboard.Dto.MemberForm;
import jpastudy.jpaboard.domain.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class MemberRepository {

    private final EntityManager em;

    // 회원 저장하기
    public void save(Member member){

        em.persist(member);

    }

    // 회원 찾기
    public Member findOne(Long id){
        return em.find(Member.class,id);
    }

    
    // 회원 이름으로 찾기
    public List<Member> findByName(String name){
        return em.createQuery("select m from Member m " +
                                      "where m.userName = :name",Member.class)
                .setParameter("name",name)
                .getResultList();
    }


    //모든 회원 찾기
    public List<Member> membersAll(){
        return em.createQuery("select m from Member m")
                .getResultList();
    }

    public List<Member> signinMember(MemberForm memberForm){

        String user_Name = memberForm.getUserName();
        Long pass_word = memberForm.getPassword();

        return em.createQuery("select m from Member m where m.userName" +
                                     " = :name and m.password = :pass_word", Member.class)
                .setParameter("name",user_Name)
                .setParameter("pass_word",pass_word)
                .getResultList();

    }

}
