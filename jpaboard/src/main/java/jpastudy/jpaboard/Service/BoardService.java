package jpastudy.jpaboard.Service;

import jpastudy.jpaboard.Dto.BoardForm;
import jpastudy.jpaboard.Repository.BoardJpaRepository;
import jpastudy.jpaboard.Repository.BoardRepository;
import jpastudy.jpaboard.domain.Board;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class BoardService {


    private final BoardRepository boardRepository;

    //게시글 전부 가져오기
    public List<Board> findBoards(){

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

}
