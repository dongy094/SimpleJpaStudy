package jpastudy.jpaboard.Controller;

import jpastudy.jpaboard.Repository.BoardRepository;
import jpastudy.jpaboard.Repository.MemberRepository;
import jpastudy.jpaboard.domain.Board;
import jpastudy.jpaboard.domain.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class HomeController {

    @RequestMapping("/")
    public String home(){
        return "home";
    }

}
