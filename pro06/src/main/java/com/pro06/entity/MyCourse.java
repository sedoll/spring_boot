package com.pro06.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

// 강좌 테이블
// 여기에 나중에 선생님 관련 컬럼 하나 추가

@Entity
@Getter
@Setter
@Table(name="mycourse")
@ToString

// column에 defualt 값을 설정할 때에 밑의 두개를 같이 써줘야 한다.
@DynamicInsert
@DynamicUpdate
public class MyCourse extends BaseEntity{

    @Id
    @Column(name = "no")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer no;         // 번호

    private String id;          // 수강신청자

    @ColumnDefault("'y'")
    private String state;       // 수강상태

    @ManyToOne(cascade = CascadeType.REMOVE, fetch = FetchType.LAZY)
    @JoinColumn(name = "cno", referencedColumnName = "no")
    private Course course;      // 강좌 번호
}
