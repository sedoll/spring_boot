package com.chunjae.test03.utils;

import com.chunjae.test03.entity.UserInfo;

public class EmailSocket {
    public void sendMail(UserInfo userInfo) {
        // 메일 보내기
        String email = userInfo.getEmail();
        String id = userInfo.getId();
        String tel = userInfo.getTel();
    }
}
