package jpastudy.jpaboard.Controller;

import jpastudy.jpaboard.Dto.BoardForm;
import jpastudy.jpaboard.Dto.CommentForm;
import jpastudy.jpaboard.Repository.BoardJpaRepository;
import jpastudy.jpaboard.Service.BoardService;
import jpastudy.jpaboard.Service.MemberService;
import jpastudy.jpaboard.domain.Board;
import jpastudy.jpaboard.domain.Comment;
import jpastudy.jpaboard.domain.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.time.LocalDateTime;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class BoardController {

    private final BoardService boardService;
    private final MemberService memberService;
    private final BoardJpaRepository boardJpaRepository;

    @GetMapping("/board/list")
    public String board_list(Model model, @PageableDefault(size = 4) Pageable pageable){

        Page<Board> boardList = boardJpaRepository.findAll(pageable);
        int startPage = Math.max(1, boardList.getPageable().getPageNumber() - 4);
        int endPage = Math.min(boardList.getTotalPages(), boardList.getPageable().getPageNumber() + 4);
        model.addAttribute("startPage", startPage);
        model.addAttribute("endPage", endPage);

//        List<Board> boardList = boardService.findBoards();
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
    public String write(BoardForm boardForm, HttpServletRequest request){

        Board board = new Board();

        board.setUserName((String) request.getSession().getAttribute("user_Name"));

        //board.setUserName(boardForm.getUserName());
        System.out.println("=============");
        System.out.println(boardForm.getUserName());
        System.out.println("=============");



        board.setTitle(boardForm.getTitle());
        board.setContent(boardForm.getContent());

        board.setLocalDateTime(LocalDateTime.now());
        boardService.writeBoard(board);

        return "home";
    }

    @GetMapping("/board/{boardId}/edit")
    public String updateBoardForm(@PathVariable("boardId") Long boardId, Model model,HttpServletRequest request){

        Board find_board = boardService.findOne(boardId);

        Board board = new Board();

        board.setId(find_board.getId());

        board.setUserName(find_board.getUserName());
        board.setTitle(find_board.getTitle());
        board.setContent(find_board.getContent());

        board.setLocalDateTime(LocalDateTime.now());

        model.addAttribute("boardForm",board);
        HttpSession session = request.getSession();

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

        BoardForm boardForm = new BoardForm();


        boardForm.setId(find_board.getId());
        boardForm.setUserName(find_board.getUserName());
        boardForm.setTitle(find_board.getTitle());
        boardForm.setContent(find_board.getContent());

        model.addAttribute("boardForm",boardForm);

        model.addAttribute("commentForm",new CommentForm());
        return "board/view_board";
    }

    @PostMapping("/board/{boardId}/view_board")
    public String write_comment(@PathVariable("boardId") Long boardId, HttpServletRequest request, @ModelAttribute("commentForm") CommentForm commentForm){

        Long memberId = (Long) request.getSession().getAttribute("user_Id");

        System.out.println("============================");
        System.out.println("commentForm = " + commentForm.getUserComment());
        System.out.println("============================");

        boardService.save_comment(boardId,commentForm.getUserName(),commentForm.getUserComment(),memberId);

//        return "board/"+boardId+"/view_board";
//        String url = "board"+boardId+"/view_board";
//        return new ModelAndView(url);
        return "home";
    }

    @GetMapping("/board/{boardId}/remove")
    public String board_remove(@PathVariable Long boardId){

        Board find_board = boardService.findOne(boardId);
        boardService.board_remove(find_board);

        return "home";
    }

    @GetMapping("comments/test")
    public String find_comments(Long boardId, Model model){
        List<Comment> commentForms = boardService.findComments(boardId);
        model.addAttribute("comments",commentForms);
        System.out.println("commentForms = " + commentForms);
        return "home";
    }




}
