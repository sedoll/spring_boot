package com.pro06.dto;

import com.pro06.entity.BaseEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

// 강좌 테이블
// 여기에 나중에 선생님 관련 컬럼 하나 추가
// validation을 이용해 size, notnull 을 써도 되고 아니면 
// column을 이용해 length랑 null able 지정해줘도 됨

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CourseDto {

    private Integer no;         // 강좌 번호

    @Size(max = 20)
    @NotBlank
    private String id;          // 작성자(관리자)

    @Size(max = 10)
    @NotBlank
    private String level;      // 학년

    @Size(max = 100)
    @NotBlank
    private String title;       // 제목

    @Size(max = 2000)
    @NotNull
    private String content;     // 내용

    @NotNull
    private Integer cnt;        // 조회수

    @NotNull
    private Integer peo;        // 수강인원

    @NotBlank
    private Integer peo_max;    // 최대 수강인원
}
