package com.pro06.dto;

import com.pro06.entity.BaseEntity;
import com.pro06.entity.Course;
import com.pro06.entity.Lecture;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

// 사용자가 해당 강의를 얼마나 들었는지 확인하는 테이블

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MyVideoDto {

    @NotBlank
    private Integer no;             // 번호

    @Size(max = 20)
    @NotBlank
    private String id;              // 수강신청자

    @NotNull
    private Integer page;           // 페이지

    @NotNull
    private Integer sec;            // 시간 초

    @Size(max = 5)
    @NotNull
    private String state;           // 모든 비디오를 봤는지 안봤는지 체크

    @NotNull
    private Course course;      // 강좌 번호

    @NotNull
    private LectureDto lecture;        // 강의 번호 외래키 지정
}
