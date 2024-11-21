package com.iset.mallapi.service;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.iset.mallapi.dto.PageRequestDTO;
import com.iset.mallapi.dto.PageResponseDTO;
import com.iset.mallapi.dto.TodoDTO;

import lombok.extern.log4j.Log4j2;

@SpringBootTest
@Log4j2
public class TodoServiceTests {

    @Autowired
    private TodoService todoService;

    @Test
    public void testRegister(){
        TodoDTO todoDTO =TodoDTO.builder()
        .title("서비스 테스트")
        .writer("tester")
        .dueDate(LocalDate.of(2024, 10, 07))
        .build();
        Long tno = todoService.register(todoDTO);

        log.info("TNO: "+tno);
    }
    
    @Test
    public void testGet(){
        Long tno = 101L;
        
        TodoDTO todoDTO = todoService.get(tno);

        log.info(todoDTO);
    }

    @Test
    public void testModify(){
        
        
        TodoDTO todoDTO = TodoDTO.builder()
        .title("Modify 테스트")
        .writer("Modifytester")
        .dueDate(LocalDate.of(2024, 10, 8))
        .complete(true)
        .tno(102L)
        .build();
        todoService.modify(todoDTO);
        log.info(todoDTO);
    }

    @Test
    public void testRemove(){
        Long tno = 103L;
        
        todoService.remove(tno);

    }

    @Test
    public void testList(){
        PageRequestDTO pageRequestDTO = PageRequestDTO.builder().page(2).size(10).build();
        PageResponseDTO<TodoDTO> response = todoService.list(pageRequestDTO);
        log.info(response);
    }
}
