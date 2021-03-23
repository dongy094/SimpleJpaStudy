package jpastudy.jpaboard.Service;

import jpastudy.jpaboard.Dto.BoardForm;
import jpastudy.jpaboard.Dto.CommentForm;
import jpastudy.jpaboard.Repository.BoardRepository;
import jpastudy.jpaboard.Repository.MemberRepository;
import jpastudy.jpaboard.domain.Board;
import jpastudy.jpaboard.domain.Comment;
import jpastudy.jpaboard.domain.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class BoardService {


    private final BoardRepository boardRepository;
    private final MemberRepository memberRepository;


    //게시글 전부 가져오기
    public List<Board> findBoards(Long boardId){

        return boardRepository.findAll();
    }

    //
    public Board findOne(Long boardId){
        return boardRepository.findOne(boardId);
    }

    
    //게시글 작성하기
    @Transactional //디폴트가 false
    public Long writeBoard(Board board){
        boardRepository.save(board);
        return board.getId();
    }

    // 변경 감지를 통한 게시글 수정
    @Transactional
    public Board updateBoard(Long boardId, BoardForm boardForm){
        Board findBoard = boardRepository.findOne(boardId);
        findBoard.setTitle(boardForm.getTitle());
        findBoard.setContent(boardForm.getContent());

        return findBoard;
    }

    @Transactional
    public void board_remove(Board find_board){
        boardRepository.remove(find_board);
    }


    // commentFn
    @Transactional
    public void save_comment(Long boardId, String userName ,String userComment, Long userId){

        // 댓글작성자 id, board id, comment내용 필요

        Board board = boardRepository.findOne(boardId);
        //Member member = memberRepository.findOne(memberId);

        // comment 생성
        Comment comment = Comment.createComment(board, userName,userComment,userId);
        
        // comment 저장
        boardRepository.save_comment(comment);

    }

    public List<Comment> findComments(Long boardId){
        return boardRepository.findAllComments(boardId);
    }



}
