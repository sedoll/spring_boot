package com.pro06.dto;

import com.pro06.entity.BaseEntity;
import com.pro06.entity.Course;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

// 나의 학습방 테이블
// 사용자가 수강신청을 하면 여기에 저장되며 강의를 수강할 수 있다.

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MyCourseDto {

    @NotBlank
    private Integer no;         // 번호

    @Size(max = 20)
    @NotBlank
    private String id;          // 수강신청자

    @Size(max = 5)
    @NotNull
    private String state;       // 수강상태

    @ManyToOne(cascade = CascadeType.REMOVE, fetch = FetchType.LAZY)
    @JoinColumn(name = "cno", referencedColumnName = "no")
    private Course course;      // 강좌 번호
}
