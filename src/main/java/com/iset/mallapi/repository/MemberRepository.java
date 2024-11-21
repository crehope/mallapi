package com.iset.mallapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.iset.mallapi.domain.Member;

public interface MemberRepository extends JpaRepository<Member,Long>{
    
}
