package com.base.jpaproject.main.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest //web테스트
class TestJpaControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Test
    void testGet() throws Exception{
        mockMvc.perform(MockMvcRequestBuilders.get("/testget"))
                .andExpect(status().isOk()) // andExpect : 기대되는 값
                .andExpect(content().string("hello test get!"))
                .andDo(print());
    }

    @Test
    void testPost() throws Exception{
        mockMvc.perform(MockMvcRequestBuilders.post("/testpost")
                .contentType(MediaType.APPLICATION_JSON)
                .content("")
                ).andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.response.resultCode").value("ok"))
                .andDo(print());


    }
}