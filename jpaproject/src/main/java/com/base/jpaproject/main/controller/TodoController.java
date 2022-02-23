package com.base.jpaproject.main.controller;

import com.base.jpaproject.main.dto.TodoUpdateDto;
import com.base.jpaproject.main.entity.Todo;
import com.base.jpaproject.main.service.TodoService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    //complete flag 조회
    @GetMapping("/getComplete")
    public ResponseEntity<?> GetCompList(Boolean flag){
        List<Todo> todo = todoService.getCompTodo(flag);

        return ResponseEntity.ok(todo);
    }

    //item,complete flag 조회
    @GetMapping("/getCompName")
    public ResponseEntity<?> GetCompNameList(Boolean flag,String item){
        List<Todo> todo = todoService.getCompNameTodo(flag,item);

        return ResponseEntity.ok(todo);
    }

    //item like 조건 조회
    @GetMapping("/getNameLike")
    public ResponseEntity<?> GetNameLike(String item,String likeFlag){
        List<Todo> todo = todoService.getNameLike(item,likeFlag);

        return ResponseEntity.ok(todo);
    }

    //id 비교 조건 조회
    @GetMapping("/getIdCompare/{compare}")
    public ResponseEntity<?> GetIdCompare(
            @PathVariable("compare") String compare
            ,Long id
            ,String sort //asc,desc
    ){
        List<Todo> todo = todoService.getIdCompare(
                compare
                ,id
                ,Optional.ofNullable(sort).orElse("asc")
        );

        return ResponseEntity.ok(todo);
    }

    //1개 수정
    @PatchMapping("/updateOne")
    public ResponseEntity<?> UpdateTodo(Long id, @RequestBody TodoUpdateDto dto){
        int result = todoService.updateTodo(id,dto);

        if(result > 0){
            return ResponseEntity.ok(result);
        }else{
            return ResponseEntity.badRequest().build();
        }
    }

    //1개 삭제
    @DeleteMapping("/delOne")
    public ResponseEntity<?> DeleteTodo(Long id){
        int todo = todoService.deleteTodo(id);

        return ResponseEntity.ok(todo);
    }

}
