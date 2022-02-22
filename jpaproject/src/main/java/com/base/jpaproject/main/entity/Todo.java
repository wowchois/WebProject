package com.base.jpaproject.main.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Getter
@NoArgsConstructor
public class Todo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String item;

    private Boolean completeFlag;

    @Builder
    private Todo(Long id,String item,Boolean flag){
        this.id = id;
        this.item = item;
        this.completeFlag = flag != null ? flag : false;
    }

}
