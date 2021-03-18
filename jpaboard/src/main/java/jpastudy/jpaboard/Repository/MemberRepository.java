package jpastudy.jpaboard.Repository;

import jpastudy.jpaboard.domain.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

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
        return em.createQuery("select m from Member m where m.userName = :name",Member.class)
                .setParameter("name",name)
                .getResultList();
    }


    //모든 회원 찾기
    public List<Member> membersAll(){
        return em.createQuery("select m from Member m")
                .getResultList();
    }

    public Member signinMember(String user_Name, Long pass_word){
        return em.createQuery("select m from Member m where m.userName = :name and m.password = :pass_word", Member.class)
                .setParameter("name",user_Name)
                .setParameter("pass_word",pass_word)
                .getSingleResult();
    }

}
