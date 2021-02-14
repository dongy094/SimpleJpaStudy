package jpastudy.jpaboard.Dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter @Setter
public class BoardForm {

    private Long id; // board id
    private String userName;
    private String title;
    private String content;

    private LocalDateTime localDateTime;

    private int hit;


    public int board_hit() {
        return 0;
    }
}
