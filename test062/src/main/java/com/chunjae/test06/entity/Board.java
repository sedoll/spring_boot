package com.chunjae.test06.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Board {
    private Integer id;
    private String name;
    private String password;
    private String username;
    private String email;
    private String address;
    private String tel;
    private String regdate;
    private String lev;
    private String act;
}
