package com.springboot.webservice.domain.posts;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter // 모든 필드의 getter를 자동 생성
@Entity // 테이블과 링크되는 클래스명 (데이터베이스와 맞닿은 핵심 클래스)
@NoArgsConstructor // 기본 생성자 자동 생성
public class Posts {

    @Id // PK (주로 Long 타입, AI 사용)
    @GeneratedValue(strategy = GenerationType.IDENTITY) // PK 생성규칙 정의
    private Long id;

    @Column(length = 500, nullable = false) // 테이블 컬럼값 (기본 값 외 변경이 필요한 경우 사용) 생략 가능
    private String title;

    @Column(columnDefinition = "TEXT" , nullable = false)
    private String content;

    private String author;

    // **빌더 패턴 사용**
    @Builder //생성자 선언시 생성자에 포함된 필드를 빌드에 포함
    // setter로 값을 무작정 채우는 대신 특정 필드에 필요한 값을 채워서 사용한다.
    public Posts(String title, String content, String author) {
        this.title = title;
        this.content = content;
        this.author = author;
    }
}
