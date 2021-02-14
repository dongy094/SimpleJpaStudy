package jpastudy.jpaboard.Controller;

import jpastudy.jpaboard.Dto.BoardForm;
import jpastudy.jpaboard.Service.BoardService;
import jpastudy.jpaboard.Service.MemberService;
import jpastudy.jpaboard.domain.Board;
import jpastudy.jpaboard.domain.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class BoardController {

    private final BoardService boardService;
    private final MemberService memberService;

    @GetMapping("/board/list")
    public String board_list(Model model){
        List<Board> boardList = boardService.findBoards();
        model.addAttribute("boardList",boardList);
        return "/board/list";
    }

    @GetMapping("/board/write")
    public String board_write(Model model){

        List<Member> member_list = memberService.findMembers();

        model.addAttribute("member_list",member_list);
        model.addAttribute("boardForm",new BoardForm());

        return "/board/write";
    }

    @PostMapping("board/write")
    public String write(BoardForm boardForm){

        Board board = new Board();
        board.setUserName(boardForm.getUserName());
        board.setTitle(boardForm.getTitle());
        board.setContent(boardForm.getContent());
        boardForm.board_hit();
        board.setHit(boardForm.getHit());
        board.setLocalDateTime(LocalDateTime.now());
        boardService.writeBoard(board);

        return "home";
    }

    @GetMapping("/board/{boardId}/edit")
    public String updateBoardForm(@PathVariable("boardId") Long boardId, Model model){

        Board find_board = boardService.findOne(boardId);

        Board board = new Board();

        board.setId(find_board.getId());

        board.setUserName(find_board.getUserName());
        board.setTitle(find_board.getTitle());
        board.setContent(find_board.getContent());

        board.setLocalDateTime(LocalDateTime.now());

        model.addAttribute("boardForm",board);

        return "/board/updateBoardForm";
    }

    @PostMapping("/board/{boardId}/edit")
    public String updateBoard(@PathVariable Long boardId ,@ModelAttribute("boardForm") BoardForm boardForm){

//        Board board = new Board();
//
//        board.setId(boardForm.getId());
//        // 이런 방밥 merge는 객체 생서해서 값을 세팅해주는데 값 세팅을 않해줄 경우 null 들어감 사용하지 말자 되도록
//        board.setUserName(boardForm.getUserName());
//        board.setTitle(boardForm.getTitle());
//        board.setContent(boardForm.getContent());
//
//        board.setLocalDateTime(LocalDateTime.now());
//
//
//        boardService.writeBoard(board);

          // 변경 감지를 통한 변경
          boardService.updateBoard(boardId,boardForm);
          
        
        return "home";
    }

    @GetMapping("/board/{boardId}/view_board")
    public String view_board(@PathVariable("boardId") Long boardId, Model model){

        Board find_board = boardService.findOne(boardId);
        find_board.board_hit();

        BoardForm boardForm = new BoardForm();

        boardForm.setId(find_board.getId());
        boardForm.setUserName(find_board.getUserName());
        boardForm.setTitle(find_board.getTitle());
        boardForm.setContent(find_board.getContent());
        boardForm.setHit(find_board.getHit());

        model.addAttribute("boardForm",boardForm);
        return "board/view_board";
    }

    @GetMapping("/board/{boardId}/remove")
    public String board_remove(@PathVariable Long boardId){

        Board find_board = boardService.findOne(boardId);
        boardService.board_remove(find_board);

        return "board/list";
    }
}
