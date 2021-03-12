package jpastudy.jpaboard.api;

import jpastudy.jpaboard.Service.MemberService;
import jpastudy.jpaboard.domain.Member;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
public class testapi {

    private final MemberService memberService;

    @PostMapping("/api/v1/members")
    public CreateMemberResponse saveMemberv1(@RequestBody @Valid Member member){
        Long memberId = memberService.join(member);
        return new CreateMemberResponse(memberId);
    }

    @Data
    static class CreateMemberResponse{

        public CreateMemberResponse(Long id) {
            this.id = id;
        }

        private Long id;
    }

}
