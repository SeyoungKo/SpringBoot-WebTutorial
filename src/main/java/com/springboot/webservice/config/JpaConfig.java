package com.springboot.webservice.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@Configuration
@EnableJpaAuditing // Application.java에서 실행되던 JpaAuditing을 JpaConfig 클래스로 이동시켰음.
public class JpaConfig {
}
