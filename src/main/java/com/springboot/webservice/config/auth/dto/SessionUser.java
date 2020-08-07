package com.springboot.webservice.config.auth.dto;

import com.springboot.webservice.domain.user.User;
import lombok.Getter;

import java.io.Serializable;

// OAuthAttributes에서 처음 가입 시점의 User 엔티티를 생성하고
// 인증된 사용자 정보를 담는 SessionUser 클래스를 생성
@Getter
public class SessionUser implements Serializable {
    private String name;
    private String email;
    private String picture;

    public SessionUser(User user) {
        this.name = user.getName();
        this.email = user.getEmail();
        this.picture = user.getPicture();
    }
}
