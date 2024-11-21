package com.iset.mallapi.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.iset.mallapi.dto.PageRequestDTO;
import com.iset.mallapi.dto.PageResponseDTO;
import com.iset.mallapi.dto.MemberDTO;
import com.iset.mallapi.service.MemberService;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

import java.util.Map;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;



@RestController
@RequiredArgsConstructor
@Log4j2
@RequestMapping("/api/member")
public class MemberController {
    private final MemberService service;

    @GetMapping("/{mno}")
    public MemberDTO get(@PathVariable(name="mno") Long mno) {
        return service.get(mno);
    }

    @GetMapping("/list")
    public PageResponseDTO<MemberDTO> list(PageRequestDTO pageRequestDTO) {
        return service.list(pageRequestDTO);
    }
    
    @PostMapping("/")
    public Map<String,Long> register(@RequestBody MemberDTO memberDTO) {
        Long mno =service.register(memberDTO);
        return Map.of("mno", mno);
    }

    @PutMapping("/{mno}")
    public Map<String,String> modify(@PathVariable(name="mno") Long mno, @RequestBody MemberDTO memberDTO ){
        memberDTO.setMno(mno);
        service.modify(memberDTO);
        return Map.of("RESULT","SUCCESS");
    }

    @DeleteMapping
    ("/{mno}")
    public Map<String,String> modify(@PathVariable(name="mno") Long mno){
        service.remove(mno);
        return Map.of("RESULT","SUCCESS");
    }
    
}
