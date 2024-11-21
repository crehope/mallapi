package com.iset.mallapi.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.iset.mallapi.dto.PageRequestDTO;
import com.iset.mallapi.dto.PageResponseDTO;
import com.iset.mallapi.dto.TodoDTO;
import com.iset.mallapi.service.TodoService;

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
@RequestMapping("/api/todo")
public class TodoController {
    private final TodoService service;

    @GetMapping("/{tno}")
    public TodoDTO get(@PathVariable(name="tno") Long tno) {
        return service.get(tno);
    }

    @GetMapping("/list")
    public PageResponseDTO<TodoDTO> list(PageRequestDTO pageRequestDTO) {
        return service.list(pageRequestDTO);
    }
    
    @PostMapping("/")
    public Map<String,Long> register(@RequestBody TodoDTO todoDTO) {
        Long tno =service.register(todoDTO);
        return Map.of("tno", tno);
    }

    @PutMapping("/{tno}")
    public Map<String,String> modify(@PathVariable(name="tno") Long tno, @RequestBody TodoDTO todoDTO ){
        todoDTO.setTno(tno);
        service.modify(todoDTO);
        return Map.of("RESULT","SUCCESS");
    }

    @DeleteMapping
    ("/{tno}")
    public Map<String,String> modify(@PathVariable(name="tno") Long tno){
        service.remove(tno);
        return Map.of("RESULT","SUCCESS");
    }
    
}
