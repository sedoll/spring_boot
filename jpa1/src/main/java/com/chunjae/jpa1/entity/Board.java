package com.chunjae.jpa1.entity;

import com.sun.istack.NotNull;
import lombok.*;
import org.springframework.stereotype.Service;

import javax.persistence.*;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Board extends BaseEntity {
    @Id // 기본키
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long seq; // 번호

    @Column(length = 500, nullable = false) // maxlength=500, not null 선언
    private String title; // 제목

    @Column(length = 2000, nullable = false) // maxlength=2000, not null 선언
    private String content; // 내용

    @Column(length = 50, nullable = false) // maxlength=50, not null 선언
//    @NotNull // not null 선언
    private String writer; // 작성자

    public void change(String title, String content) {
        this.title = title;
        this.content = content;
    }
}
