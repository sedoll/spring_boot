package com.pro06.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DialectOverride;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

// 강좌 테이블
// 여기에 나중에 선생님 관련 컬럼 하나 추가

@Entity
@Getter
@Setter
@Table(name="course")
@ToString

// column에 defualt 값을 설정할 때에 밑의 두개를 같이 써줘야 한다.
@DynamicInsert
@DynamicUpdate
public class Course extends BaseEntity{

    @Id
    @Column(name = "no")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer no;         // 강좌 번호
    private String id;          // 작성자(관리자)
    private String level;      // 학년
    private String title;       // 제목
    private String content;     // 내용

    @ColumnDefault("0")
    private Integer cnt;        // 조회수

    @ColumnDefault("0")
    private Integer peo;        // 수강인원

    private Integer peo_max;    // 최대 수강인원
}
