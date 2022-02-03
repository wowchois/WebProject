package com.base.jpaproject.main.controller;

import com.base.jpaproject.main.entity.Todo;
import com.base.jpaproject.main.repository.TodoRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

class TodoControllerTest {
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

}