package com.base.jpaproject.main.service;

import com.base.jpaproject.main.entity.Todo;
import com.base.jpaproject.main.repository.TodoRepository;
import io.swagger.models.auth.In;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TodoService {

    private final TodoRepository todoRepository;

    //insert
    public Todo addTodo(String item){
        Todo todo = Todo.builder()
                .item(item)
                .build();

        todo = todoRepository.save(todo);

        return todo;
    }

    //select all
    public List<Todo> getAllTodo(){
        return todoRepository.findAll();
    }

    public Optional<Todo> getTodo(Long id){
        return todoRepository.findById(id);
    }

}
