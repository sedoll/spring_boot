package com.chunjae.test06.exception;

// 유일한 필드 = unique, primary key
// 아이디, 이메일 중복방지 예외
public class DuplicateKeyException extends org.springframework.dao.DuplicateKeyException {
    public DuplicateKeyException(String msg) {
        super(msg);
    }
}
