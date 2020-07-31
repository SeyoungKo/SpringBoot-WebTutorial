package com.springboot.webservice.web;

import com.springboot.webservice.service.PostsService;
import com.springboot.webservice.web.dto.PostsSaveRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor // 롬복 생성자로 주입받기(의존성 관계가 변경될 때마다 생성자 코드를 번거롭게 수정하지 않아도 된다.)
public class PostsApiController {
    private final PostsService postsService;

    @PostMapping("/api/v1/posts")
    public Long save(@RequestBody PostsSaveRequestDto requestDto) {
        return postsService.save(requestDto);
    }
}
