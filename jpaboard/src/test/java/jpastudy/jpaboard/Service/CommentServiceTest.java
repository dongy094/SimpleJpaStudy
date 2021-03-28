package jpastudy.jpaboard.Service;

import jpastudy.jpaboard.Repository.BoardRepository;
import jpastudy.jpaboard.Repository.MemberRepository;
import jpastudy.jpaboard.domain.Board;
import jpastudy.jpaboard.domain.Comment;
import jpastudy.jpaboard.domain.Member;
import org.junit.Test;
import org.junit.jupiter.api.Order;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class CommentServiceTest {

    @Autowired BoardRepository boardRepository;
    @Autowired MemberRepository memberRepository;
    @Autowired BoardService boardService;
    @Autowired MemberService memberService;

    @Test
    public void 코멘트() throws Exception{
        //given

        Member member = new Member();
        member.setUserName("UserA");
        member.setId(123L);
        member.setPassword(1234L);

        Board board = new Board();
        board.setTitle("aaa");
        board.setContent("bbb");
        board.setId(1212L);
        board.setUserName("adsd");
        //when
//
//        Comment comment = new Comment();
//        Comment com1 = Comment.createComment(board, "오오오오오오");
//        boardRepository.save_comment(com1);

//        System.out.println("com1.getBoard() = " + com1.getBoard().getId());
//        System.out.println("com1.getMember().getId() = " + com1.getMember().getId());
//        System.out.println("com1.getUserComment() = " + com1.getUserComment());
        //then
    
    }

}
