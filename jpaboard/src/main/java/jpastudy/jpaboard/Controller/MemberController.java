package jpastudy.jpaboard.Controller;

import jpastudy.jpaboard.Dto.MemberForm;
import jpastudy.jpaboard.Repository.MemberRepository;
import jpastudy.jpaboard.Service.MemberService;
import jpastudy.jpaboard.domain.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;


import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @ExceptionHandler(IllegalStateException.class)
    public String error1(Exception e,Model model) {
        System.err.println(e.getClass());
        model.addAttribute("memberForm", new MemberForm());
        return "member/join";
    }


    // 로그아웃
    @GetMapping("/member/logout")
    public String logout(HttpServletRequest request){

        HttpSession session = request.getSession();
        session.removeAttribute("user_Id");
        return "home";
    }

    // 전체멤버 보기
    @GetMapping("/member/list")
    public String member_list(Model model){

        List<Member> members = memberService.findMembers();
        model.addAttribute("members",members);
        return "member/list";

    }

    //회원가입으로이동
    @GetMapping("/member/new")
    public String createMember(Model model){
        model.addAttribute("memberForm", new MemberForm());
        return "member/join";
    }

    //회원가입 폼 서버로 전송
    @PostMapping("/member/new")
    public String create(@Valid MemberForm memberForm,
                         BindingResult result,
                         HttpServletRequest request,
                         HttpServletResponse response ) throws IOException {

        if(result.hasErrors()){
            return "/member/join";
        }

        Member member = new Member();
        member.setUserName(memberForm.getUserName());
        member.setPassword(memberForm.getPassword());

        //유효성 검사
        memberService.validateMember(member);

        Long user_Id = memberService.join(member);

        Member user_Member = memberService.findMember(user_Id);
        HttpSession session = request.getSession();
        session.setAttribute("user_Id",user_Id);
        session.setAttribute("user_Name",user_Member.getUserName());


        return "home";

    }

    @GetMapping("/member/signin")
    public String member_signup(Model model){
        model.addAttribute("memberForm", new MemberForm());
        return "/member/signin";
    }

    @PostMapping("/member/signin")
    public String member_signup(@Valid MemberForm memberForm,
                                BindingResult result,
                                HttpServletResponse response,
                                HttpServletRequest request) throws IOException {

        if(result.hasErrors()){
            return "/member/signin";
        }
        List<Member> signin_member = memberService.signin(memberForm);

        int size = signin_member.size();
        if(size >= 1){
            HttpSession session = request.getSession();
            session.setAttribute("user_Id",signin_member.get(0).getId());
            session.setAttribute("user_Name",signin_member.get(0).getUserName());
        }else{
            return "/member/signin";
        }

        return "/home";
    }

}
