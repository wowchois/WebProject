package com.base.jpaproject.main.service;

import com.base.jpaproject.main.dto.TodoUpdateDto;
import com.base.jpaproject.main.entity.Todo;
import com.base.jpaproject.main.repository.TodoRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional
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

    //select one
    public Optional<Todo> getTodo(Long id){
        return todoRepository.findById(id);
    }

    public List<Todo> getCompTodo(Boolean flag){
        return todoRepository.findByCompleteFlag(flag);
    }

    public List<Todo> getCompNameTodo(Boolean flag,String item){
        return todoRepository.findByCompleteFlagAndItem(flag,item);
    }

    public List<Todo> getNameLike(String item,String likeFlag){
        List<Todo> list = new ArrayList<>();
        if("0".equals(likeFlag)){ //포함
            list = todoRepository.findByItemContains(item);
        }else if("1".equals(likeFlag)){ //시작
            list = todoRepository.findByItemStartsWith(item);
        }else if("2".equals(likeFlag)){ //끝
            list = todoRepository.findByItemEndsWith(item);
        }

        return list;
    }

    public List<Todo> getIdCompare(String flag, Long id, String sortFlag){
        List<Todo> list = new ArrayList<>();
        Sort sort = Sort.by("desc".equals(sortFlag) ? Sort.Direction.DESC : Sort.Direction.ASC
                ,"id");

        if("greater".equals(flag)){ // >
            list = todoRepository.findByIdGreaterThan(id,sort);
        }else if("greaterEqual".equals(flag)){ // >=
            list = todoRepository.findByIdGreaterThanEqual(id,sort);
        }else if("less".equals(flag)){ // <
            list = todoRepository.findByIdLessThan(id,sort);
        }else if("lessEqual".equals(flag)){ // <=
            list = todoRepository.findByIdLessThanEqual(id,sort);
        }

        return list;
    }

    //update one
    public int updateTodo(Long id,TodoUpdateDto data){
        Optional<Todo> todo = todoRepository.findById(id);

        todo.ifPresent(t -> {
            t = t.builder()
                    .id(id)
                    .item(data.getItem() != null ? data.getItem() : t.getItem())
                    .flag(data.getCompleteFlag() != null ? data.getCompleteFlag() : t.getCompleteFlag())
                    .build();
            this.todoRepository.save(t);
        });

        return todo.isPresent() ? 1 : 0;
    }

    public int deleteTodo(Long id){
        Optional<Todo> todo = this.todoRepository.findById(id); //this.getTodo(id);

        if(todo.isPresent()){
            this.todoRepository.delete(todo.get());
            return 1;
        }
        return 0;
    }

}
