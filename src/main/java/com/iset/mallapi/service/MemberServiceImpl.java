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

import com.iset.mallapi.domain.Member;
import com.iset.mallapi.dto.MemberDTO;
import com.iset.mallapi.dto.PageRequestDTO;
import com.iset.mallapi.dto.PageResponseDTO;
import com.iset.mallapi.repository.MemberRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Service
@Transactional
@Log4j2
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {
    
    private final ModelMapper modelMapper;

    private final MemberRepository memberRepository;

    @Override
    public Long register (MemberDTO memberDTO){
        log.info("...............");
        Member member = modelMapper.map(memberDTO,Member.class);
        member.setMemberNow(true);
        member.setRegiDate(LocalDate.now());
        member.setModDate(LocalDate.now());
        Member savedMember =memberRepository.save(member);
        return savedMember.getMno();
    } 

    @Override
    public MemberDTO get(Long mno){
        Optional<Member> result =memberRepository.findById(mno);
        Member member =result.orElseThrow();
        MemberDTO dto =modelMapper.map(member,MemberDTO.class);
        return dto;
    }

    @Override
    public void modify(MemberDTO memberDTO){
        Optional<Member> result = memberRepository.findById(memberDTO.getMno());
        Member member =result.orElseThrow();
        member.setName(memberDTO.getName());
        member.setGender(memberDTO.getGender());
        member.setAge(memberDTO.getAge());
        member.setModDate(LocalDate.now());
        memberRepository.save(member);
    }

    @Override
    public void remove(Long mno){
        memberRepository.deleteById(mno);
    }

        @Override
    public PageResponseDTO<MemberDTO> list (PageRequestDTO pageRequestDTO){
        Pageable pageable = PageRequest.of(
            pageRequestDTO.getPage() -1,//1페이지가 0이므로
            pageRequestDTO.getSize(),
            Sort.by("mno").descending()
        );

        Page<Member> result = memberRepository.findAll(pageable);
        List<MemberDTO> dtoList =result.getContent().stream().map(member->modelMapper.map(member,MemberDTO.class)).collect(Collectors.toList());

        long totalCount = result.getTotalElements();

        PageResponseDTO<MemberDTO> responseDTO= 
        PageResponseDTO.<MemberDTO>withAll()
                      .dtoList(dtoList)
                      .pageRequestDTO(pageRequestDTO)
                      .totalCount(totalCount)
                      .build();

        return responseDTO;
    }
}
