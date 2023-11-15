package com.chunjae.jpa1.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Player {               // 직원
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long no;                // 직원번호
    private String name;            // 이름
    private String position;        // 담당
    private Integer age;            // 나이

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "team_no")
    private Team team;
}
