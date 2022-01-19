package com.base.jpaproject.main.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestJpaController {

    @GetMapping("/testget")
    public String testGet(){
        return "hello test get!";
    }

    @PostMapping("/testpost")
    public String testPost(){
        return "hello test post!";
    }
}
