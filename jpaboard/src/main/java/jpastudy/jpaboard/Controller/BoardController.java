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
import org.dom4j.rule.Mode;
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

        //model.addAttribute("member_list",member_list);
        model.addAttribute("boardForm",new BoardForm());

        return "/board/write";
    }

    @PostMapping("board/write")
    public String write(BoardForm boardForm, HttpServletRequest request){

        Long userId = (Long) request.getSession().getAttribute("user_Id");
        //Long userId = memberService.findUserIdByName(boardForm.getUserName());

        //0402
        //Board board = new Board();

        //0402
        boardForm.setLocalDateTime(LocalDateTime.now());
        boardService.save_board(userId,boardForm);

        //0402
        //board.setUserName((String) request.getSession().getAttribute("user_Name"));
        //board.setUserName(boardForm.getUserName());

        //0402
//        board.setTitle(boardForm.getTitle());
//        board.setContent(boardForm.getContent());
//        board.setLocalDateTime(LocalDateTime.now());
//
//        boardService.writeBoard(board);

        return "home";
    }

    @GetMapping("/board/{boardId}/edit")
    public String updateBoardForm(@PathVariable("boardId") Long boardId,
                                  Model model,HttpServletRequest request){

        Board find_board = boardService.findOne(boardId);

        BoardForm boardForm = new BoardForm();
        boardForm.setId(boardId);
        boardForm.setTitle(find_board.getTitle());
        boardForm.setContent(find_board.getContent());
        boardForm.setUserName(find_board.getMember().getUserName());
        boardForm.setLocalDateTime(find_board.getLocalDateTime());

        model.addAttribute("boardForm",boardForm);

        return "/board/updateBoardForm";

        ////0402ni
//        Board board = new Board();
//
//        board.setId(find_board.getId());
//
//        //0402
//        Long memberId = (Long) request.getSession().getAttribute("user_Id");
//        Member member = memberService.findMember(memberId);
//        board.setMember(member);
//
//        //board.setUserName(find_board.getUserName());
//
//        board.setTitle(find_board.getTitle());
//        board.setContent(find_board.getContent());
//
//        board.setLocalDateTime(LocalDateTime.now());
//
//        //0402
//        board.setComments(find_board.getComments());

        ////0402ni


    }

    @PostMapping("/board/{boardId}/edit")
    public String updateBoard(@PathVariable Long boardId,
                              BoardForm boardForm){
          boardService.updateBoard(boardId,boardForm);
          return "home";

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

    }

    @GetMapping("/board/{boardId}/view_board")
    public String view_board(@PathVariable("boardId") Long boardId, Model model,HttpServletRequest request){

        //0402
        Long memberId = (Long) request.getSession().getAttribute("user_Id");
        Member member = memberService.findMember(memberId);

        Board find_board = boardService.findOne(boardId);
        List<Comment> commentForms = boardService.findComments(boardId);

        BoardForm boardForm = new BoardForm();
        boardForm.setId(find_board.getId());

        boardForm.setUserName(member.getUserName());
        boardForm.setTitle(find_board.getTitle());
        boardForm.setContent(find_board.getContent());

        model.addAttribute("boardForm",boardForm);
        model.addAttribute("comments",commentForms);
        model.addAttribute("commentForm",new CommentForm());
        return "board/view_board";

        //0402 boardForm.setUserName(find_board.getUserName());
    }

    //  comment insert
    @PostMapping("/board/{boardId}/view_board")
    public String write_comment(@PathVariable("boardId") Long boardId,
                         HttpServletRequest request,
                         CommentForm commentForm,
                         Model model){

        Long memberId = (Long) request.getSession().getAttribute("user_Id");

        Member member = memberService.findMember(memberId);

        boardService.save_comment(boardId, member.getUserName(),
                                    commentForm.getUserComment(),memberId);

        Board find_board = boardService.findOne(boardId);
        List<Comment> commentForms = boardService.findComments(boardId);

        BoardForm boardForm = new BoardForm();
        boardForm.setId(find_board.getId());
        boardForm.setUserName(member.getUserName());
        boardForm.setTitle(find_board.getTitle());
        boardForm.setContent(find_board.getContent());

        model.addAttribute("boardForm",boardForm);
        model.addAttribute("comments",commentForms);
        model.addAttribute("commentForm",new CommentForm());
        return "board/view_board";

    }

    // 보드 댓글 가져오는거 됨 이거 view에 뿌려야됨
    // form에 담아서 뿌리는게 좋음
    // Test
    @GetMapping("comments/test")
    public String find_comments(Long boardId, Model model){
        List<Comment> commentForms = boardService.findComments(1L);
        model.addAttribute("comments",commentForms);

        System.out.println("commentForms = " + commentForms.get(0).getUserName());

        return "home";
    }
    // Test


    @GetMapping("/board/{boardId}/remove")
    public String board_remove(@PathVariable Long boardId){

        Board find_board = boardService.findOne(boardId);
        boardService.board_remove(find_board);

        return "home";

    }




}
