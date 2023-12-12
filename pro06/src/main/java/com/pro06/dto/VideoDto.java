package com.pro06.dto;

import com.pro06.entity.BaseEntity;
import com.pro06.entity.Course;
import com.pro06.entity.Lecture;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

// 강의 영상 테이블
// 강의 영상 정보를 저장

@Data
@NoArgsConstructor
@AllArgsConstructor
public class VideoDto extends BaseEntity {

    @NotBlank
    private Integer no;             // 영상번호

    @Size(max = 255)
    @NotBlank
    private String savefolder;      // 저장경로

    @Size(max = 255)
    @NotBlank
    private String originfile;      // 실제 파일 이름

    @Size(max = 255)
    @NotBlank
    private String savefile;        // 저장된 파일 이름

    @Size(max = 255)
    @NotBlank
    private Long filesize;          // 파일 크기

    @NotNull
    private CourseDto course;        // 강좌 번호 외래키 지정

    @NotNull
    private LectureDto lecture;        // 강의 번호 외래키 지정
}
