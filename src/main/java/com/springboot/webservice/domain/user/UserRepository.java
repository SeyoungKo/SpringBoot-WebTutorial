package com.springboot.webservice.domain.user;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

// User CRUD
public interface UserRepository extends JpaRepository<User, Long> {
    // 소셜로그인에서 반환하는 값 중 email로 이미 생성된 사용자인지 판단하기 위한 메소드
    Optional<User> findByEmail(String email);
}
