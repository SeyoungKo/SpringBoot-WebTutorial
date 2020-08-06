package com.springboot.webservice.web;

import com.springboot.webservice.service.PostsService;
import com.springboot.webservice.web.dto.PostsResponseDto;
import com.springboot.webservice.web.dto.PostsSaveRequestDto;
import com.springboot.webservice.web.dto.PostsUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor // 롬복 생성자로 주입받기(의존성 관계가 변경될 때마다 생성자 코드를 번거롭게 수정하지 않아도 된다.)
public class PostsApiController {
    private final PostsService postsService;

    // insert (save)
    @PostMapping("/api/v1/posts")
    public Long save(@RequestBody PostsSaveRequestDto requestDto) { // HTTP 요청 내용을 posts 객체에 매핑
        return postsService.save(requestDto);
    }

    // update
    @PutMapping("/api/v1/posts/{id}")
    public Long update(@PathVariable Long id, @RequestBody PostsUpdateRequestDto requestDto){
        return postsService.update(id, requestDto);
    }

    // select (find)
    @GetMapping("/api/v1/posts/{id}")
    public PostsResponseDto findById(@PathVariable Long id){
        return postsService.findById(id);
    }
}
