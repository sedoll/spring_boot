package com.pro06.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

@Entity
@Getter
@Setter
@Table(name="lectest")
@ToString

// column에 defualt 값을 설정할 때에 밑의 두개를 같이 써줘야 한다.
@DynamicInsert
@DynamicUpdate
public class LecTest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer no;         // 시험 번호
    
    // 문제 + 4지선다
    private String exam1;
    private String exam2;
    private String exam3;
    private String exam4;
    private String exam5;
    
    // 해당 문제의 답안
    private String answer1;
    private String answer2;
    private String answer3;
    private String answer4;
    private String answer5;
    
    @ManyToOne(cascade = CascadeType.REMOVE, fetch = FetchType.LAZY)
    @JoinColumn(name = "cno", referencedColumnName = "no")
    private Course course;        // 강좌 번호 외래키 지정

    @ManyToOne(cascade = CascadeType.REMOVE, fetch = FetchType.LAZY)
    @JoinColumn(name = "lno", referencedColumnName = "no")
    private Lecture lecture;        // 강의 번호 외래키 지정
}
