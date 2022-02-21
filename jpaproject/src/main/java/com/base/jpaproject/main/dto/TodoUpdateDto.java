package com.base.jpaproject.main.dto;

import com.base.jpaproject.main.entity.Todo;
import lombok.Data;
import lombok.Getter;

@Getter
//@Data
public class TodoUpdateDto {
    private String item;
    private Boolean completeFlag;

    public TodoUpdateDto(Todo todo){
        this.item = todo.getItem();
        this.completeFlag = todo.getCompleteFlag();
    }
}
