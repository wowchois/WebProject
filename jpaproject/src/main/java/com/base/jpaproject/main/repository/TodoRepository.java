package com.base.jpaproject.main.repository;

import com.base.jpaproject.main.entity.Todo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TodoRepository extends JpaRepository<Todo,Long> {

    List<Todo> findByCompleteFlag(Boolean flag);

    List<Todo> findByCompleteFlagAndItem(Boolean flag,String item);

    List<Todo> findByItemContains(String item);

    List<Todo> findByItemStartsWith(String item);

    List<Todo> findByItemEndsWith(String item);

}
