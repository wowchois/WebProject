package com.base.jpaproject.main.controller;

import com.base.jpaproject.main.service.FeignTestService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class FeignTestController {

    private final FeignTestService feignTestService;

    @GetMapping("/feigntest")
    public void feignTest(){
        feignTestService.callFeignTest();
    }

}
