package com.springboot.webservice.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller // 클라이언트 요청에 뷰를 반환하는 역할
public class IndexController {

    @GetMapping("/")
    public String index(){
        return "index"; //src/main/resources/templates/index.mustache 반환
    }
}
