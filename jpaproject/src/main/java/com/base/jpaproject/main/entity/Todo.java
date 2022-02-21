package com.base.jpaproject.main.entity;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Getter
@DynamicUpdate
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Todo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String item;

    private Boolean completeFlag;

    @Builder
    private Todo(String item,Boolean flag){
        this.item = item;
        this.completeFlag = flag != null ? flag : false;
    }

    public void update(String item, Boolean flag){
        this.item = item;
        this.completeFlag = flag;
    }

}
