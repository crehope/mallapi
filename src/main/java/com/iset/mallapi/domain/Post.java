package com.iset.mallapi.domain;

import java.time.LocalDate;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "tbl_post")
@Getter
@ToString
@Builder
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Post {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long pno;

    private String title;

    private String writer;

    private String content;

    private Long mno;

    private boolean isDelete;

    private LocalDate regiDate;

    private LocalDate modDate;
}
