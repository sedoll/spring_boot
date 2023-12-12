package com.pro06.dto;

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
public class LecTestDto {

    @NotBlank
    private Integer no;         // 시험 번호
    
    // 문제 + 4지선다
    @Size(max = 100)
    @NotBlank
    private String exam1;

    @Size(max = 100)
    @NotBlank
    private String exam2;

    @Size(max = 100)
    @NotBlank
    private String exam3;

    @Size(max = 100)
    @NotBlank
    private String exam4;

    @Size(max = 100)
    @NotBlank
    private String exam5;
    
    // 해당 문제의 답안
    @Size(max = 100)
    @NotBlank
    private String answer1;

    @Size(max = 100)
    @NotBlank
    private String answer2;

    @Size(max = 100)
    @NotBlank
    private String answer3;

    @Size(max = 100)
    @NotBlank
    private String answer4;

    @Size(max = 100)
    @NotBlank
    private String answer5;
    
    @ManyToOne(cascade = CascadeType.REMOVE, fetch = FetchType.LAZY)
    @JoinColumn(name = "cno", referencedColumnName = "no")
    private Course course;        // 강좌 번호 외래키 지정

    @ManyToOne(cascade = CascadeType.REMOVE, fetch = FetchType.LAZY)
    @JoinColumn(name = "lno", referencedColumnName = "no")
    private Lecture lecture;        // 강의 번호 외래키 지정
}
