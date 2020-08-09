package com.springboot.webservice.web;

import com.springboot.webservice.config.auth.LoginUser;
import com.springboot.webservice.config.auth.dto.SessionUser;
import com.springboot.webservice.domain.user.User;
import com.springboot.webservice.service.PostsService;

import com.springboot.webservice.web.dto.PostsResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.servlet.http.HttpSession;

@RequiredArgsConstructor // 롬복 생성자로 주입받기(의존성 관계가 변경될 때마다 생성자 코드를 번거롭게 수정하지 않아도 된다.)
@Controller // 클라이언트 요청에 뷰를 반환하는 역할
public class IndexController {

    private final PostsService postsService;
    private final HttpSession httpSession;

    // PostService.findAllDesc() 결과를 index.mustache에 전달
    @GetMapping("/")
    public String index(Model model, @LoginUser SessionUser user){  // @LoginUser ~ - httpSession.getAttribute("user")로 가져오던 세션 값을 개선
        model.addAttribute("posts", postsService.findAllDesc());

        // --- @LoginUser의 사용으로 개선되어 주석처리 (세션값을 가져오는 부분이 중복적으로 사용될 수 있으므로 개선) ---
//        SessionUser user= (SessionUser) httpSession.getAttribute("user"); // CustomOAuth2UserService에서 로그인 후 세션에 저장한 정보들을 가져온다.

        if(user!=null){
            model.addAttribute("userName", user.getName()); // index.mustache에 userName 값 반환
        }
        return "index"; //src/main/resources/templates/index.mustache 반환
    }

    @GetMapping("/posts/save")
    public String postsSave(){
        return "posts-save";

    }

    @GetMapping("/posts/update/{id}")
    public String postsUpdate(@PathVariable Long id, Model model){
        PostsResponseDto dto = postsService.findById(id);
        model.addAttribute("post", dto);
        return "posts-update";
    }
}
