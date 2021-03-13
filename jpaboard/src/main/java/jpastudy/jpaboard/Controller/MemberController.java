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


import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @GetMapping("/member/list")
    public String member_list(Model model){

        List<Member> members = memberService.findMembers();
        model.addAttribute("members",members);
        return "member/list";

    }

    @GetMapping("/member/new")
    public String createMember(Model model){
        model.addAttribute("memberForm", new MemberForm());
        return "member/join";
    }

    //회원가입 폼 서버로 전송
    @PostMapping("/member/new")
    public String create(@Valid MemberForm memberForm, BindingResult result){

        if(result.hasErrors()){
            return "/member/join";
        }

        Member member = new Member();
        member.setUserName(memberForm.getUserName());

        int result_check = memberService.validateMember(member);
        if(result_check==1){

            return "/member/join";
        }else{
            memberService.join(member);

            return "home";
        }


    }

}
