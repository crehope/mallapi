package com.iset.mallapi.domain;

import java.time.LocalDate;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "tbl_member")
@Getter
@ToString
@Builder
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Member {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long mno;

    private String name;

    private String gender;//M,F

    private int age;

    private boolean isMemberNow;

    private LocalDate regiDate;

    private LocalDate modDate;

    private LocalDate quitDate;
}
