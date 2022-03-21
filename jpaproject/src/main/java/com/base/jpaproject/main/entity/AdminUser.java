package com.base.jpaproject.main.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class AdminUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String username;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String email;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role;

    @Builder
    public AdminUser(String username, String password, String email, Role role){
        this.username = username;
        this.password = password;
        this.email = email;
        this.role = role;
    }

//    public AdminUser update(String name, String picture){
//        this.name = name;
//        this.picture = picture;
//
//        return this;
//    }

    public String getRoleLevel(){
        return this.role.getLevel();
    }

}
