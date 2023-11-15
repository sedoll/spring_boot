package com.chunjae.jpa1.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Seat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "seat_no")       // 실제 db 컬럼 이름을 원하는 걸로 지정,
    private Long no;

    private String rowNo;           // 행
    private String colNo;           // 열

    @OneToOne(mappedBy = "seat", fetch = FetchType.LAZY)
    private Student student;
}
