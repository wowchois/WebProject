package com.base.jpaproject.main.controller;

import com.base.jpaproject.main.entity.Todo;
import com.base.jpaproject.main.repository.TodoRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@EnableWebMvc
class TodoControllerTest {
    @Autowired
    private MockMvc mockMvc;

    /*
    @Autowired
    private final TodoRepository todoRepository;

    TodoControllerTest(TodoRepository todoRepository) {
        this.todoRepository = todoRepository;
    }

    @Test
    void todoTest(){
        Todo todo = Todo.builder().item("test").build();

        this.todoRepository.save(todo);
    }
    */
    @Test
    void todoWebTest() throws Exception{
        mockMvc.perform(MockMvcRequestBuilders.get("/todo/add"))
                .andExpect(status().isOk())
                .andDo(print());
    }

}