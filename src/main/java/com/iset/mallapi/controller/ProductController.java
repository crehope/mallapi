package com.iset.mallapi.controller;

import java.util.List;
import java.util.Map;

import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import com.iset.mallapi.dto.PageRequestDTO;
import com.iset.mallapi.dto.PageResponseDTO;
import com.iset.mallapi.dto.ProductDTO;
import com.iset.mallapi.service.ProductService;
import com.iset.mallapi.util.CustomFileUtil;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@RestController
@RequiredArgsConstructor
@Log4j2
@RequestMapping("/api/products")
public class ProductController {

    private final ProductService productService; 
    private final CustomFileUtil fileUtil;

    @PostMapping("/")
    public Map<String, Long> register(ProductDTO productDTO) {
        
        log.info("register: " + productDTO);

        List<MultipartFile> files = productDTO.getFiles();
        List<String> uploadFileNames = fileUtil.saveFiles(files);

        productDTO.setUploadFileNames(uploadFileNames);

        log.info(uploadFileNames);

        // 서비스 호출
        Long pno = productService.register(productDTO);

        return Map.of("result", pno);
    }

    @GetMapping("/view/{fileName}")
    public ResponseEntity<Resource> viewFileGET(@PathVariable String fileName) {

        return fileUtil.getFile(fileName);
    }
    
    @GetMapping("/list")
    public PageResponseDTO<ProductDTO> list(PageRequestDTO pageRequestDTO) {
        log.info("list..............." + pageRequestDTO);

        return productService.getList(pageRequestDTO);
    }

    @GetMapping("/{pno}")
    public ProductDTO read(@PathVariable(name="pno") Long pno) {

        return productService.get(pno);
    }
    
}