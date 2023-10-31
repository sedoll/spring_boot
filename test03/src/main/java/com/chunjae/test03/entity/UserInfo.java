package com.chunjae.test03.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class UserInfo {
    private Integer no;         // 고유번호
    private String id;          // 아이디
    private String pw;          // 비밀번호
    private String name;        // 이름
    private String email;       // 이메일
    private String addr;        // 주소
    private String tel;         // 전화번호
    private String regdate;     // 가입일
    private String lev;         // 레벨
    private String act;         // 활성, 비활성 상태
    private Integer pt;         // 포인트
}
