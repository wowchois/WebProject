package com.base.jpaproject.main.repository;

import com.base.jpaproject.main.entity.AdminUser;
import com.base.jpaproject.main.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AdminUserRepository extends JpaRepository<AdminUser,Long> {
    public AdminUser findByUsername(String username);
}
