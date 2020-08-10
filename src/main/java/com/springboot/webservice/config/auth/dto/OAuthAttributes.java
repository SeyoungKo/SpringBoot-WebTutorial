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
        if("naver".equals(registrationId)) {
            return ofNaver("id", attributes);
        }
        return ofGoogle(userNameAttributeName, attributes);
    }

    private static OAuthAttributes ofNaver(String userNameAttributeName, Map<String, Object> attributes){
        Map<String, Object> response = (Map<String, Object>) attributes.get("response"); // 네이버 오픈 API 로그인 회원 결과 중 최상위 필드 중 response 필드를 가져온다.
        return OAuthAttributes.builder()
                .name((String) response.get("name")) // "response" :  {   "name" : "user1",
                .email((String) response.get("email"))                 // "email" : "test@naver.com",
                .picture((String) response.get("profile_image"))           // "profile_image" : "https://..." }
                .attributes(response)
                .nameAttributeKey(userNameAttributeName) // userNameAttributeName : OAuth2 로그인 시 키가 되는 필드 값 (PK)
                .build();
    }

    private static OAuthAttributes ofGoogle(String userNameAttributeName, Map<String, Object> attributes) {
        return OAuthAttributes.builder()
                .name((String) attributes.get("name"))
                .email((String) attributes.get("email"))
                .picture((String) attributes.get("picture"))
                .attributes(attributes)
                .nameAttributeKey(userNameAttributeName) // userNameAttributeName : OAuth2 로그인 시 키가 되는 필드 값 (PK)
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
