package com.chunjae.jpa1.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long no;

    private String id;

    @OneToOne(fetch = FetchType.LAZY)   // seat 테이블과의 연관관계 1 : 1
    @JoinColumn(name = "seat_no")
    private Seat seat;
}
