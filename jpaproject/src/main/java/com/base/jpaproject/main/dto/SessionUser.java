package com.base.jpaproject.main.dto;

import com.base.jpaproject.main.entity.User;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.io.Serializable;

@Getter
public class SessionUser implements Serializable {
    private String name;
    private String email;
    private String picture;

    public SessionUser(User user){
        this.name = user.getName();
        this.email = user.getEmail();
        this.picture = user.getPicture();
    }
}
