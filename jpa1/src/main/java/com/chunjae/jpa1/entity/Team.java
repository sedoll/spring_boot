package com.chunjae.jpa1.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
public class Team {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "team_no")
    private Long no;                // 부서 번호

    @Column(name = "team_name")
    private String name;            // 부서명

    @OneToMany(mappedBy = "team", fetch = FetchType.LAZY)
    private List<Player> playerList;
}
