package com.iset.mallapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.iset.mallapi.domain.Todo;

public interface TodoRepository extends JpaRepository<Todo,Long>{
    
}
