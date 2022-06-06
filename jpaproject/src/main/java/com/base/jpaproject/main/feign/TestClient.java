package com.base.jpaproject.main.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Map;

@FeignClient(name="testfeign", url="${feign.callurl}")
public interface TestClient {

    @GetMapping(value = "/test"
            , produces = "application/json")
    Map<String,Object> getTestApi();
}
