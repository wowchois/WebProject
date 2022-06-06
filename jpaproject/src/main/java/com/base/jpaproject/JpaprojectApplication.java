package com.base.jpaproject;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
public class JpaprojectApplication {

    public static void main(String[] args) {
        SpringApplication.run(JpaprojectApplication.class, args);
    }

}
