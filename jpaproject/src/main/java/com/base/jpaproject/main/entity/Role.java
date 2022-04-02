package com.base.jpaproject.main.entity;

import lombok.Getter;

@Getter
public enum Role {
    GUEST("ROLE_GUEST","GUEST"),
    MANAGER("ROLE_MANAGER","MANAGER"),
    ADMIN("ROLE_ADMIN","ADMIN");

    private final String level;
    private final String role;

    Role(String level,String role){
        this.level = level;
        this.role = role;
    }
}
