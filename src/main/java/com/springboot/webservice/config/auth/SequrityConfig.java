package com.springboot.webservice.config.auth;

import com.springboot.webservice.domain.user.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@RequiredArgsConstructor
@EnableWebSecurity // Spring Sequrity 설정을 활성화한다.
public class SequrityConfig extends WebSecurityConfigurerAdapter {

    private final CustomOAuth2UserService customOAuth2UserService;

    @Override
    protected void configure(HttpSecurity http) throws Exception{
        http
                .csrf().disable()
                .headers().frameOptions().disable() // h2 console 화면 사용
                .and()
                    .authorizeRequests() // URL별 권한 관리를 설정하는 시작점
                    .antMatchers("/","/css/**","/images/**","/js/**", "/h2-console/**").permitAll() // / URL은 모두 접근 가능
                    .antMatchers("/api/v1/**").hasRole(Role.USER.name())  // /api/v1/~ 은 USER 권한인 사람만 접근 가능
                    .anyRequest().authenticated() // 설정된 값 이외 나머지 URL을 인증된 사용자만 접근할 수 있도록 한다. (authenticated())
                .and()
                    .logout()
                        .logoutSuccessUrl("/") // 로그아웃 성공시 / URL로 이동한다.
                .and()
                    .oauth2Login() // oauth2 로그인에 대한 설정 진입점
                        .userInfoEndpoint() // oauth2 로그인 성공 이후 사용자 정보를 가져올 때 설정
                            .userService(customOAuth2UserService); // 소셜 로그인 성공 시 userService 인터페이스 구현체를 등록한다. (로그인 성공시 추가로 진행하고자 하는 기능을 명시할 수 있다.)

    }
}
