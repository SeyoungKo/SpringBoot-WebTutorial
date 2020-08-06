package com.springboot.webservice.web;

import com.springboot.webservice.service.PostsService;
<<<<<<< HEAD
import com.springboot.webservice.web.dto.PostsResponseDto;
=======
>>>>>>> develop
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RequiredArgsConstructor // 롬복 생성자로 주입받기(의존성 관계가 변경될 때마다 생성자 코드를 번거롭게 수정하지 않아도 된다.)
@Controller // 클라이언트 요청에 뷰를 반환하는 역할
public class IndexController {

    private final PostsService postsService;

    @GetMapping("/")
    public String index(Model model){ // PostService.findAllDesc() 결과를 index.mustache에 전달
        model.addAttribute("posts", postsService.findAllDesc());
        return "index"; //src/main/resources/templates/index.mustache 반환
    }

    @GetMapping("/posts/save")
    public String postsSave(){
        return "posts-save";
    }
<<<<<<< HEAD

    @GetMapping("/posts/update/{id}")
    public String postsUpdate(@PathVariable Long id, Model model){
        PostsResponseDto dto = postsService.findById(id);
        model.addAttribute("post", dto);
        return "posts-update";
    }
=======
>>>>>>> develop
}
