package jpastudy.jpaboard.Repository;

import jpastudy.jpaboard.Dto.CommentForm;
import jpastudy.jpaboard.domain.Board;
import jpastudy.jpaboard.domain.Comment;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.lang.reflect.Member;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class BoardRepository {

    private final EntityManager em;

    //
    public Board findOne(Long id){

        return em.find(Board.class,id);

    }

    // 작성한 글 저장하기
    public void save(Board board){

        if(board.getId() == null){
            em.persist(board);
        }else{
            em.merge(board);
        }

    }

    // 모든 글 반환하기
    public List<Board> findAll(){
        return em.createQuery("select b from Board b",Board.class)
                .getResultList();
    }

    public void remove(Board find_board){
        em.remove(find_board);
    }

    // commentFn

    public void save_comment(Comment comment){
        em.persist(comment);
    }

    public List<Comment> findAllComments(Long boardId){
        return em.createQuery("select c from Comment c where board_id" +
                                      " = :boardId", Comment.class)
                .setParameter("boardId",boardId)
                .getResultList();
    }



}
