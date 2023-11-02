package com.chunjae.test04.exception;

// 데이터 Not Null 예외 처리
public class DataIntegrityViolationException extends org.springframework.dao.DataIntegrityViolationException {
    public DataIntegrityViolationException(String msg) {
        super(msg);
    }
}
