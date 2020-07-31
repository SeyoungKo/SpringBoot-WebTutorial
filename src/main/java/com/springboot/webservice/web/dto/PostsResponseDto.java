package com.springboot.webservice.web.dto;

import com.springboot.webservice.domain.posts.Posts;
import lombok.Getter;

@Getter

// === controller에 entity에 저장된 데이터를 전달하는 ResponseDto ===
public class PostsResponseDto {
    private Long id;
    private String title;
    private String content;
    private String author;

    public PostsResponseDto(Posts entity){
        this.id = entity.getId();
        this.title = entity.getTitle();
        this.content = entity.getContent();
        this.author = entity.getAuthor();
    }
}
