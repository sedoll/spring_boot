package com.pro06.dto;

import com.pro06.entity.BaseEntity;
import com.pro06.entity.Course;
import com.pro06.entity.Lecture;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LecAnsDto {

    @NotBlank
    private Integer no;         // 시험 번호

    @Size(max = 20)
    @NotBlank
    private String id;
    
    // 제출된 답안
    @Size(max = 20)
    @NotBlank
    private String answer1;         // 1번

    @Size(max = 100)
    @NotBlank
    private String answer2;         // 2번

    @Size(max = 100)
    @NotBlank
    private String answer3;         // 3번

    @Size(max = 100)
    @NotBlank
    private String answer4;         // 4번

    @Size(max = 100)
    @NotBlank
    private String answer5;         // 5번

    @NotBlank
    private Integer ansCnt;         // 맞은 개수
    
    @ManyToOne(cascade = CascadeType.REMOVE, fetch = FetchType.LAZY)
    @JoinColumn(name = "cno", referencedColumnName = "no")
    private Course course;        // 강좌 번호 외래키 지정

    @ManyToOne(cascade = CascadeType.REMOVE, fetch = FetchType.LAZY)
    @JoinColumn(name = "lno", referencedColumnName = "no")
    private Lecture lecture;        // 강의 번호 외래키 지정
}
