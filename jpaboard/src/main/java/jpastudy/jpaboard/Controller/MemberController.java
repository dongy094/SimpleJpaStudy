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

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;


import javax.persistence.TypedQuery;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @GetMapping("/member/logout")
    public String logout(HttpServletRequest request){

        HttpSession session = request.getSession();
        session.removeAttribute("user_Id");
        return "/home";
    }

    @GetMapping("/member/list")
    public String member_list(Model model){

        List<Member> members = memberService.findMembers();
        model.addAttribute("members",members);
        return "member/list";

    }

    //회원가입버튼
    @GetMapping("/member/new")
    public String createMember(Model model){
        model.addAttribute("memberForm", new MemberForm());
        return "member/join";
    }

    //회원가입 폼 서버로 전송
    @PostMapping("/member/new")
    public String create(@Valid MemberForm memberForm, BindingResult result, HttpServletRequest request, HttpServletResponse response ) throws IOException {

        if(result.hasErrors()){
            return "/member/join";
        }

        Member member = new Member();
        member.setUserName(memberForm.getUserName());
        member.setPassword(memberForm.getPassword());

        int result_check = memberService.validateMember(member);
        if(result_check==1){
            // 중복아이디 존재
            response.setContentType("text/html; charset=UTF-8");
            PrintWriter out = response.getWriter();
            out.println("<script>alert('중복된 아이디 입니다.'); history.go(-1);</script>");
            out.flush();

            return "/member/join";
        }else{
            Long user_Id = memberService.join(member);
            Member user_Member = memberService.findMember(user_Id);
            HttpSession session = request.getSession();
            session.setAttribute("user_Id",user_Id);
            session.setAttribute("user_Name",user_Member.getUserName());

            return "home";
        }

    }

    @GetMapping("/member/signin")
    public String member_signup(Model model){
        model.addAttribute("memberForm", new MemberForm());
        return "/member/signin";
    }

    @PostMapping("/member/signin")
    public String member_signup(@Valid MemberForm memberForm, HttpServletRequest request){

        Member signin_member = memberService.signin(memberForm.getUserName(), memberForm.getPassword());
        if(signin_member!=null){
            HttpSession session = request.getSession();
            session.setAttribute("user_Id",signin_member.getId());
            session.setAttribute("user_Name",signin_member.getUserName());
        }

        return "/home";
    }

}
