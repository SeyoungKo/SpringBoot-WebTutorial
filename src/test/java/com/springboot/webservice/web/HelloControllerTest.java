package com.springboot.webservice.web;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.io.IOException;

import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class) // Junit 실행자 외 다른 실행자(SpringRunner) 실행  = 연결자 역할
@WebMvcTest // Web관련에 집중하는 어노테이션 (@Controller)
public class HelloControllerTest {
    @Autowired
    private MockMvc mvc; // API 테스트

    @Test
    public void return_hello() throws Exception{
        String hello = "hello";

        mvc.perform(get("/hello"))
                                  .andExpect(status().isOk())
                                  .andExpect(content().string(hello));// MockMVC로 /hello에 GET 요청
    }
}