package jpastudy.jpaboard.domain;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@SequenceGenerator(
        name="COMMENT_SEQ_GENERATOR",
        sequenceName = "COMMENT_SEQ",
        initialValue = 1, allocationSize = 1 )
public class Comment {

    @Id @GeneratedValue(strategy = GenerationType.SEQUENCE,
            generator = "COMMENT_SEQ_GENERATOR" )
    @Column(name = "comment_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "board_id")
    private Board board;

//    @OneToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "member_id")
//    private Member member;

//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "member_id")
//    private Member member;

    private String userName;
    private String userComment;
    private Long userId;

    // 연관관계 메서드 //
    public void setBoard(Board board){
        this.board = board;
        board.getComments().add(this);
    }

//    public void setMember(Member member){
//        this.member = member;
//        member.getComments().add(this);
//    }
    // 연관관계 메서드 //
//    public void setMember(Member member){
//        this.member = member;
//        member.setComment(this);
//    }

    // 생성 메서드 //
    public static Comment createComment(Board board,
                                        String userName,
                                        String comments,
                                        Long userId){
        Comment comment = new Comment();
        comment.setBoard(board);
        comment.setUserName(userName);
        comment.setUserId(userId);
        comment.setUserComment(comments);
        return comment;
    }

}
