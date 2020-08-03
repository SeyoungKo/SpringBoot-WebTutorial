package com.springboot.webservice.web;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController // JSON/ XML 객체 반환하는 역할 (Controller에 @ResponseBody가 추가됨)
public class HelloController {

    @GetMapping("/hello")
    public String hello(){
        return "hello";
    }
}
