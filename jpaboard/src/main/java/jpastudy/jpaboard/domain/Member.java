package jpastudy.jpaboard.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter @Setter
@Entity
@SequenceGenerator(
        name="MEMBER_SEQ_GENERATOR",
        sequenceName = "MEMBER_SEQ",
        initialValue = 1, allocationSize = 1 )
public class Member {

    @Id @GeneratedValue(strategy = GenerationType.SEQUENCE,
            generator = "MEMBER_SEQ_GENERATOR" )
    @Column(name = "member_id")
    private Long id;

    private String userName;

    private Long password;

//    @OneToMany(mappedBy = "member")
//    private List<Comment> comments = new ArrayList<>();


//    @OneToOne(mappedBy = "member", fetch = FetchType.LAZY)
//    private Comment comment;


}
