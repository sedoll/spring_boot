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
    private Integer userId;
    private Integer active;
    private String loginId;
    private String password;
    private String userName;
    private String passwordConfirm;
}
