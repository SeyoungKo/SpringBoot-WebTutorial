package com.springboot.webservice.web.dto;

import com.springboot.webservice.domain.posts.Posts;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
// Entity 클래스(Posts)는 비즈니스 로직, 데이터베이스 관련 작업을 위한 클래스
// Entity 클래스(Posts)가 변경되면 여러 클래스에 영향을 주지만 Request/Response Dto는
// 뷰를 위한 클래스라 자주 변경이 되는 클래스이다. (View Layer와 DB Layer를 분리하는 것이 좋다. )
// Dto는 Controller에서 결과값을 조인해서 줘야할 때 유용하게 사용될 수 있다.

// === 데이터 삽입시 controller에서 사용하는 RequestDto ===
public class PostsSaveRequestDto {
    private String title;
    private String content;
    private String author;

    @Builder
    public PostsSaveRequestDto(String title, String content, String author){
        this.title = title;
        this.content = content;
        this.author = author;
    }
    public Posts toEntity(){
        return Posts.builder().title(title)
                              .content(content)
                              .author(author)
                              .build();
    }
}
