package com.iset.mallapi.service;

import com.iset.mallapi.dto.MemberDTO;
import com.iset.mallapi.dto.PageRequestDTO;
import com.iset.mallapi.dto.PageResponseDTO;

public interface MemberService {
    Long register(MemberDTO memberDTO);

    MemberDTO get(Long mno);
    
    void modify(MemberDTO memberDTO);

    void remove(Long mno);

    PageResponseDTO<MemberDTO> list (PageRequestDTO pageRequestDTO);
}
