package com.iset.mallapi.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.iset.mallapi.domain.Todo;
import com.iset.mallapi.dto.PageRequestDTO;
import com.iset.mallapi.dto.PageResponseDTO;
import com.iset.mallapi.dto.TodoDTO;
import com.iset.mallapi.repository.TodoRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Service
@Transactional
@Log4j2
@RequiredArgsConstructor
public class TodoServiceImpl implements TodoService {
    
    private final ModelMapper modelMapper;

    private final TodoRepository todoRepository;

    @Override
    public Long register (TodoDTO todoDTO){
        log.info("...............");
        Todo todo = modelMapper.map(todoDTO,Todo.class);
        Todo savedTodo =todoRepository.save(todo);
        return savedTodo.getTno();
    } 

    @Override
    public TodoDTO get(Long tno){
        Optional<Todo> result =todoRepository.findById(tno);
        Todo todo =result.orElseThrow();
        TodoDTO dto =modelMapper.map(todo,TodoDTO.class);
        return dto;
    }

    @Override
    public void modify(TodoDTO todoDTO){
        Optional<Todo> result = todoRepository.findById(todoDTO.getTno());
        Todo todo =result.orElseThrow();
        todo.setTitle(todoDTO.getTitle());
        todo.setDueDate(todoDTO.getDueDate());
        todo.setComplete(todoDTO.isComplete());
        todo.setWriter(todoDTO.getWriter());
        todoRepository.save(todo);
    }

    @Override
    public void remove(Long tno){
        todoRepository.deleteById(tno);
    }

    @Override
    public PageResponseDTO<TodoDTO> list (PageRequestDTO pageRequestDTO){
        Pageable pageable = PageRequest.of(
            pageRequestDTO.getPage() -1,//1페이지가 0이므로
            pageRequestDTO.getSize(),
            Sort.by("tno").descending()
        );

        Page<Todo> result = todoRepository.findAll(pageable);
        List<TodoDTO> dtoList =result.getContent().stream().map(todo->modelMapper.map(todo,TodoDTO.class)).collect(Collectors.toList());

        long totalCount = result.getTotalElements();

        PageResponseDTO<TodoDTO> responseDTO= 
        PageResponseDTO.<TodoDTO>withAll()
                      .dtoList(dtoList)
                      .pageRequestDTO(pageRequestDTO)
                      .totalCount(totalCount)
                      .build();

        return responseDTO;
    }
}
