package jpastudy.jpaboard.Inrerceptor;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
public class WebMvcConfig extends WebMvcConfigurerAdapter {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new MemberInterceptor())                // 등록할 인터셉터
                .addPathPatterns("/board/*")                            //적용할 패턴, ex) /*모든 경로
                .addPathPatterns("/member/*")
                .excludePathPatterns("/board/list")                      // 인터셉터 예외
                .excludePathPatterns("/member/new")                         //회원가입 쪽은 예외처리를 한다.
                .excludePathPatterns("/member/signin");                     //로그인 쪽은 예외처리를 한다.

    }

}
