package com.iset.mallapi.repository;

import java.time.LocalDate;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import com.iset.mallapi.domain.Todo;

import lombok.extern.log4j.Log4j2;

@SpringBootTest
@Log4j2
public class TodoRepositoryTests {

    @Autowired
    private TodoRepository todoRepository;

    @Test
    public void testInsert(){
        for(int i=1;i<=100;i++ ){
            Todo todo =Todo.builder()
            .title("Title..."+i)
            .dueDate(LocalDate.of(2024,10,04))
            .writer("user00")
            .build();
            todoRepository.save(todo);
        }
    }

    @Test
    public void testRead(){
        Long tno =33L;
        Optional<Todo> result =todoRepository.findById(tno);
        Todo todo = result.orElseThrow();
        log.info(todo);
    }

    @Test
    public void testModify(){
        Long tno =33L;
        Optional<Todo> result =todoRepository.findById(tno);
        Todo todo = result.orElseThrow();
        todo.setTitle("Modified 33...");
        todo.setComplete(true);
        todo.setDueDate(LocalDate.of(2024,10,05));
        todoRepository.save(todo);
    }

    @Test
    public void testDelete(){
        Long tno =1L;
        todoRepository.deleteById(tno);
    }

    @Test
    public void testPaging(){
        Pageable pageable = PageRequest.of(0,10,Sort.by("tno").descending());
        Page<Todo> result= todoRepository.findAll(pageable); 
        log.info(result.getTotalElements());
        result.getContent().stream().forEach(todo->log.info(todo));
        result.getContent().stream().forEach(todo->System.out.println(todo));
    }
}
