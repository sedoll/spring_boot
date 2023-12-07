package com.pro06.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

// 사용자가 해당 강의를 얼마나 들었는지 확인하는 테이블

@Entity
@Getter
@Setter
@Table(name="myvideo")
@ToString

// column에 defualt 값을 설정할 때에 밑의 두개를 같이 써줘야 한다.
@DynamicInsert
@DynamicUpdate
public class MyVideo extends BaseEntity{

    @Id
    @Column(name = "no")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer no;             // 번호

    private String id;              // 수강신청자

    @ColumnDefault("0")
    private Integer page;           // 페이지

    @ColumnDefault("0")
    private Integer sec;            // 시간 초
    
    @ColumnDefault("'n'")
    private String state;           // 모든 비디오를 봤는지 안봤는지 체크


    @ManyToOne(cascade = CascadeType.REMOVE, fetch = FetchType.LAZY)
    @JoinColumn(name = "cno", referencedColumnName = "no")
    private Course course;      // 강좌 번호

    @ManyToOne(cascade = CascadeType.REMOVE, fetch = FetchType.LAZY)
    @JoinColumn(name = "lno", referencedColumnName = "no")
    private Lecture lecture;        // 강의 번호 외래키 지정
}
