package com.springboot.webservice;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

//@EnableJpaAuditing // JPA Auditing 활성화 (Entity에 데이터 생성,수정 시간을 포함)
// @WebMvcTest에서 엔티티 클래스 생성 에러 방지를 위해 @SpringBootApplication에서 JpaAuditing이 사용되지 않도록 주석처리.(JpaConfig로 이동)
@SpringBootApplication
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class,args);
    }
}
