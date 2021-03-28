package jpastudy.jpaboard.Dto;

import jpastudy.jpaboard.domain.Board;
import jpastudy.jpaboard.domain.Member;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class CommentForm {

    // comment id
   // private Long id;
    //

//    private Long boardId;
//    private Long userId;

    private String userName;
    private String userComment;

}
