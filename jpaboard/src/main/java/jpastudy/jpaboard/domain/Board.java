package jpastudy.jpaboard.domain;

import jpastudy.jpaboard.Dto.BoardForm;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter @Setter
@Entity
@SequenceGenerator(
        name="BOARD_SEQ_GENERATOR",
        sequenceName = "BOARD_SEQ",
        initialValue = 1, allocationSize = 1 )
public class Board {

    @Id @GeneratedValue(strategy = GenerationType.SEQUENCE,
        generator = "BOARD_SEQ_GENERATOR" )
    @Column(name = "board_id")
    private Long id;

    //0402  private String userName;
    private String title;
    private String content;

    private LocalDateTime localDateTime;

    @OneToMany(mappedBy = "board")
    private List<Comment> comments = new ArrayList<>();



    //0402
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    //0402
    //연관관계 메서드

    public void setMember(Member member) {
        this.member = member;
        member.getBoards().add(this);
    }

    //0402
    //생성 메서드
    public static Board createBoard(Member member,
                                    BoardForm boardForm){

        Board board = new Board();
        board.setTitle(boardForm.getTitle());
        board.setContent(boardForm.getContent());
        board.setLocalDateTime(boardForm.getLocalDateTime());
        board.setMember(member);
        return board;

    }
    // comment는x 초기에는 없음

}
