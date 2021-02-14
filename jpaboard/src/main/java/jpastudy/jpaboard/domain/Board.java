package jpastudy.jpaboard.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter @Setter
@Entity
@SequenceGenerator(
        name="BOARD_SEQ_GENERATOR",
        sequenceName = "BOARD_SEQ",
        initialValue = 1, allocationSize = 1 )
public class Board {

    @Id @GeneratedValue(strategy = GenerationType.SEQUENCE,
        generator = "BOARD_SEQ_GENERATOR" )
    private Long id;

    private String userName;
    private String title;
    private String content;

    private LocalDateTime localDateTime;

    private int hit;

    public void board_hit(){
        this.hit += 1;
    }

}
