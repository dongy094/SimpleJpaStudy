package jpastudy.jpaboard.domain;

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

    private String userName;
    private String title;
    private String content;

    private LocalDateTime localDateTime;

    @OneToMany(mappedBy = "board")
    private List<Comment> comments = new ArrayList<>();


}
