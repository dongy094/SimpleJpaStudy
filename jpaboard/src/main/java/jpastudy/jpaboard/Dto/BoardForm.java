package jpastudy.jpaboard.Dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import java.time.LocalDateTime;

@Getter @Setter
public class BoardForm {

    private Long id; // board id
    private String userName;

    //@NotEmpty
    private String title;
    //@NotEmpty
    private String content;

    private LocalDateTime localDateTime;


}
