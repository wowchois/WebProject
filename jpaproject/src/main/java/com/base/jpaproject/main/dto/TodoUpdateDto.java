package com.base.jpaproject.main.dto;

import lombok.Getter;

@Getter
public class TodoUpdateDto {
    private String item;
    private Boolean completeFlag;
}
