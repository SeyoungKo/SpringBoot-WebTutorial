package com.springboot.webservice.config.auth.dto;

import com.springboot.webservice.domain.user.Role;
import com.springboot.webservice.domain.user.User;
import lombok.Builder;
import lombok.Getter;

import java.util.Map;

// 인증된 사용자 정보를 저장하기 위한 dto
@Getter
public class OAuthAttributes { // User클래스를 Service에서 바로 사용하면 직렬화오류 발생
    // 직렬화 기능을 가진 OAuthAttributes 세션 Dto를 추가한 것
    private Map<String, Object> attributes;
    private String nameAttributeKey;
    private String name;
    private String email;
    private String picture;

    @Builder
    public OAuthAttributes(Map<String, Object> attributes, String nameAttributeKey, String name, String email, String picture) {
        this.attributes = attributes;
        this.nameAttributeKey = nameAttributeKey;
        this.name = name;
        this.email = email;
        this.picture = picture;
    }

    //Map 타입의 사용자 정보를 하나하나 반환해야 한다.
    public static OAuthAttributes of(String registrationId, String userNameAttributeName, Map<String, Object> attributes) {
        return ofGoogle(userNameAttributeName, attributes);
    }

    private static OAuthAttributes ofGoogle(String userNameAttributeName, Map<String, Object> attributes) {
        return OAuthAttributes.builder()
                .name((String) attributes.get("name"))
                .email((String) attributes.get("email"))
                .picture((String) attributes.get("picture"))
                .attributes(attributes)
                .nameAttributeKey(userNameAttributeName)
                .build();
    }



    // OAuthAttributes에서 User엔티티를 생성 (처음 가입시)
    // 클래스 생성이 끝나면 SessionUser클래스를 생성
    public User toEntity() {
        return User.builder()
                .name(name)
                .email(email)
                .picture(picture)
                .role(Role.GUEST)
                .build();
    }
}
