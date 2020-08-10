package com.springboot.webservice.web;

import com.springboot.webservice.config.auth.SecurityConfig;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class) // Junit 실행자 외 다른 실행자(SpringRunner) 실행  = 연결자 역할
@WebMvcTest(controllers = HelloController.class, // Web관련에 집중하는 어노테이션 (@Controller) - JPA가 작동하지 않을 때 테스트 수행시 사용
        excludeFilters = {
                @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = SecurityConfig.class) // SecurityConfig를 WebMvcTest 스캔 대상에서 제외한다.
        }
) // WebMvcTest는 WebSecurityConfigurerAdapter, WebMvcConfigurer, Controller를 읽는다.
// (@Service, @Component는 스캔 대상이 아니기 때문에 excludeFilters로 제외해준다.)
public class HelloControllerTest {
    @Autowired
    private MockMvc mvc; // API 테스트

    @WithMockUser(roles="USER") // 모의 사용자 생성 (roles= USER 권한을 부여한다.)
    @Test
    public void return_hello() throws Exception {
        String hello = "hello";

        mvc.perform(get("/hello"))
                .andExpect(status().isOk())
                .andExpect(content().string(hello)); // MockMVC로 /hello에 GET 요청
    }

    @WithMockUser(roles="USER") // 모의 사용자 생성 (roles= USER 권한을 부여한다.)
    @Test
    public void return_helloDto() throws Exception {
        String name = "hello";
        int amount = 1000;

        mvc.perform(
                get("/hello/dto")
                        .param("name", name)
                        .param("amount", String.valueOf(amount)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", is(name)))
                .andExpect(jsonPath("$.amount", is(amount)));
    }
}