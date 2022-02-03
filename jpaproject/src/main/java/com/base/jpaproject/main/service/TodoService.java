package com.base.jpaproject.main.service;

import com.base.jpaproject.main.entity.Todo;
import com.base.jpaproject.main.repository.TodoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TodoService {

    private final TodoRepository todoRepository;

    public Todo addTodo(String item){
        Todo todo = Todo.builder()
                .item(item)
                .build();

        todo = todoRepository.save(todo);

        return todo;
    }

}
