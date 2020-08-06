package com.springboot.webservice.web.dto;

import com.springboot.webservice.domain.posts.Posts;
import lombok.Getter;

import java.time.LocalDateTime;

// findAll() 호출시 List 반환
@Getter
public class PostsListResponseDto {
    private Long id;
    private String title;
    private String author;
    private LocalDateTime modifiedDate;

    public PostsListResponseDto(Posts entity){
        this.id = entity.getId();
        this.title = entity.getTitle();
        this.author = entity.getAuthor();
        this.modifiedDate = entity.getModifiedDate();
    }
}
