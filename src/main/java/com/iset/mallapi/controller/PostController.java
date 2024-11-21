package com.iset.mallapi.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.iset.mallapi.dto.PageRequestDTO;
import com.iset.mallapi.dto.PageResponseDTO;
import com.iset.mallapi.dto.PostDTO;
import com.iset.mallapi.service.PostService;

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
@RequestMapping("/api/posting")
public class PostController {
    private final PostService service;

    @GetMapping("/{pno}")
    public PostDTO get(@PathVariable(name="pno") Long pno) {
        return service.get(pno);
    }

    @GetMapping("/list")
    public PageResponseDTO<PostDTO> list(PageRequestDTO pageRequestDTO) {
        return service.list(pageRequestDTO);
    }
    
    @PostMapping("/")
    public Map<String,Long> register(@RequestBody PostDTO postDTO) {
        Long pno =service.register(postDTO);
        return Map.of("pno", pno);
    }

    @PutMapping("/{pno}")
    public Map<String,String> modify(@PathVariable(name="pno") Long pno, @RequestBody PostDTO postDTO ){
        postDTO.setPno(pno);
        service.modify(postDTO);
        return Map.of("RESULT","SUCCESS");
    }

    @DeleteMapping
    ("/{pno}")
    public Map<String,String> modify(@PathVariable(name="pno") Long pno){
        service.remove(pno);
        return Map.of("RESULT","SUCCESS");
    }
    
}
