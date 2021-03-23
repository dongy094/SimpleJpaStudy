package jpastudy.jpaboard.Dto;

import jpastudy.jpaboard.domain.Board;
import jpastudy.jpaboard.domain.Member;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class CommentForm {

    private String userName;
    private String userComment;

}
