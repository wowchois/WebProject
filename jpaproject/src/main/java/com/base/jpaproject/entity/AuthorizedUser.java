package com.base.jpaproject.entity;

import lombok.Getter;

import javax.persistence.*;

@Entity
@Getter
public class AuthorizedUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long expire;

}
