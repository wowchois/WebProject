package com.base.jpaproject.main.service;

import com.base.jpaproject.main.feign.TestClient;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
@Slf4j
@RequiredArgsConstructor
public class FeignTestService {

    private final TestClient testClient;

    public void callFeignTest(){
        Map<String,Object> result = testClient.getTestApi();
        log.info("## result {}",result);
    }
}
