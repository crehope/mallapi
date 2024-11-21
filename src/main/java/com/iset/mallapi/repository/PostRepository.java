package com.iset.mallapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.iset.mallapi.domain.Post;

public interface PostRepository extends JpaRepository<Post,Long>{
    
}
