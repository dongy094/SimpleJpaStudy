package jpastudy.jpaboard.Dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;

@Getter @Setter
public class MemberForm {

    @NotEmpty(message = "이름을 입력하세요")
    private String userName;

}
