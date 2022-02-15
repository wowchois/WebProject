package com.base.jpaproject.main.controller;

import com.base.jpaproject.main.entity.Todo;
import com.base.jpaproject.main.service.TodoService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/todo")
public class TodoController {

    private final TodoService todoService;

    //추가
    @PostMapping("/add")
    public ResponseEntity<?> AddTodo(String item){
        Todo todo = todoService.addTodo(item);
        return ResponseEntity.ok(todo);
    }

    //전체조회
    @GetMapping("/getAll")
    public ResponseEntity<?> GetAllTodo(){
        List<Todo> list = todoService.getAllTodo();
        return ResponseEntity.ok(list);
    }

    //id 1개 조회
    @GetMapping("/getOne")
    public ResponseEntity<?> GetTodo(Long id){
        Optional<Todo> todo = todoService.getTodo(id);
        if(todo.isPresent()){
            return ResponseEntity.ok(todo);
        }else{
            return ResponseEntity.badRequest().build();
        }
    }

}
