package com.iset.mallapi.service;

import java.time.LocalDate;
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

import com.iset.mallapi.domain.Post;
import com.iset.mallapi.dto.PageRequestDTO;
import com.iset.mallapi.dto.PageResponseDTO;
import com.iset.mallapi.dto.PostDTO;
import com.iset.mallapi.repository.PostRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Service
@Transactional
@Log4j2
@RequiredArgsConstructor
public class PostServiceImpl implements PostService {
    
    private final ModelMapper modelMapper;

    private final PostRepository postRepository;

    @Override
    public Long register (PostDTO postDTO){
        log.info("...............");
        Post post = modelMapper.map(postDTO,Post.class);
        post.setDelete(false);
        post.setRegiDate(LocalDate.now());
        post.setModDate(LocalDate.now());
        Post savedPost =postRepository.save(post);
        return savedPost.getPno();
    } 

    @Override
    public PostDTO get(Long pno){
        Optional<Post> result =postRepository.findById(pno);
        Post post =result.orElseThrow();
        PostDTO dto =modelMapper.map(post,PostDTO.class);
        return dto;
    }

    @Override
    public void modify(PostDTO postDTO){
        Optional<Post> result = postRepository.findById(postDTO.getPno());
        Post post =result.orElseThrow();
        post.setTitle(postDTO.getTitle());
        post.setContent(postDTO.getContent());
        post.setModDate(LocalDate.now());
        postRepository.save(post);
    }

    @Override
    public void remove(Long pno){
        postRepository.deleteById(pno);
    }

        @Override
    public PageResponseDTO<PostDTO> list (PageRequestDTO pageRequestDTO){
        Pageable pageable = PageRequest.of(
            pageRequestDTO.getPage() -1,//1페이지가 0이므로
            pageRequestDTO.getSize(),
            Sort.by("pno").descending()
        );

        Page<Post> result = postRepository.findAll(pageable);
        List<PostDTO> dtoList =result.getContent().stream().map(post->modelMapper.map(post,PostDTO.class)).collect(Collectors.toList());

        long totalCount = result.getTotalElements();

        PageResponseDTO<PostDTO> responseDTO= 
        PageResponseDTO.<PostDTO>withAll()
                      .dtoList(dtoList)
                      .pageRequestDTO(pageRequestDTO)
                      .totalCount(totalCount)
                      .build();

        return responseDTO;
    }
}
