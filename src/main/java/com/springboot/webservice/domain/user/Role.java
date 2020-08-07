package com.springboot.webservice.domain.user;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

// User 권한 관리하는 Enum 클래스
@Getter
@RequiredArgsConstructor
public enum Role {

    GUEST("ROLE_GUEST", "손님"),
    USER("ROLE_USER", "일반 사용자");

    private final String key;
    private final String title;
}
