package com.base.jpaproject.main.entity;

import lombok.Getter;

@Getter
public enum Role {
    GUEST("ROLE_GUEST","GUEST"),
    SUPER("ROLE_SUPER","SUPER");

    private final String level;
    private final String role;

    Role(String level,String role){
        this.level = level;
        this.role = role;
    }
}
