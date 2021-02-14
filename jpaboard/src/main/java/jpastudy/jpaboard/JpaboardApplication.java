package jpastudy.jpaboard;

import jpastudy.jpaboard.Service.MemberService;
import jpastudy.jpaboard.domain.Member;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Service;

@SpringBootApplication
public class JpaboardApplication {

	public static void main(String[] args) {

		SpringApplication.run(JpaboardApplication.class, args);
	}

}
