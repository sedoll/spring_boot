package com.chunjae.test04.utils;

import com.chunjae.test04.entity.UserInfo;

public class EmailSocket {
    public void sendMail(UserInfo userInfo) {
        // 메일 보내기
        String email = userInfo.getEmail();
        String id = userInfo.getId();
        String tel = userInfo.getTel();
    }
}
