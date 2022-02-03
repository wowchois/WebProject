package com.base.jpaproject.main.controller;

import com.base.jpaproject.main.entity.Todo;
import com.base.jpaproject.main.service.TodoService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/todo")
public class TodoController {

    private final TodoService todoService;

    @GetMapping("/add")
    public ResponseEntity<Todo> AddTodo(String item){
        Todo todo = todoService.addTodo(item);
        return ResponseEntity.ok(todo);
    }

}
