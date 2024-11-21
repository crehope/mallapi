package com.iset.mallapi.service;

import com.iset.mallapi.dto.PageRequestDTO;
import com.iset.mallapi.dto.PageResponseDTO;
import com.iset.mallapi.dto.PostDTO;

public interface PostService {
    Long register(PostDTO postDTO);

    PostDTO get(Long pno);
    
    void modify(PostDTO postDTO);

    void remove(Long pno);

    PageResponseDTO<PostDTO> list (PageRequestDTO pageRequestDTO);
}
