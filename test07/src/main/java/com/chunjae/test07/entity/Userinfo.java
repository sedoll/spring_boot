package com.chunjae.test07.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Userinfo {
    private Integer user_id;
    private Integer active;
    private String id;
    private String pw;
    private String userName;
    private String pwConfirm;
}
