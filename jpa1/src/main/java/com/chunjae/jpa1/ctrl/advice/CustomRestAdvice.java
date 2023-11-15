package com.chunjae.jpa1.ctrl.advice;

import lombok.extern.log4j.Log4j2;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;

// Restful API의 AOP Exception 처리
@RestControllerAdvice
@Log4j2
public class CustomRestAdvice {

    // BindException
    @ExceptionHandler(BindException.class) // 예외처리 메소드 선언
    @ResponseStatus(HttpStatus.EXPECTATION_FAILED) // 예외처리가 제대로 되지 않은 경우
    public ResponseEntity<Map<String, String>> handleBindException(BindException e) { // Map<String, String> 예외이름, 예외 메세지
        log.error(e);
        Map<String, String> errMap = new HashMap<>();
        if(e.hasErrors()) {
            BindingResult bindingResult = e.getBindingResult(); // 에러 메세지 가져오기
            bindingResult.getFieldErrors().forEach(fieldError -> {
                errMap.put(fieldError.getField(), fieldError.getCode()); // 에러 이름, 메세지를 map에 저장
            });
        }
        return ResponseEntity.badRequest().body(errMap);
    }

    // 외래키 관련 예외처리
    // DataIntegrityViolationException
    @ExceptionHandler(DataIntegrityViolationException.class) // 예외처리 메소드 선언
    @ResponseStatus(HttpStatus.EXPECTATION_FAILED) // 예외처리가 제대로 되지 않은 경우
    public ResponseEntity<Map<String, String>> handleFKException(Exception e) {
        log.error(e);
        Map<String, String> errMap = new HashMap<>();
        errMap.put("time", System.currentTimeMillis()+""); // 에러 이름, 메세지를 map에 저장
        errMap.put("msg", "constraint fails"); // 에러 이름, 메세지를 map에 저장
        return ResponseEntity.badRequest().body(errMap);
    }

    // NoSuchElementException(EmptyResultDataAccessException)
    @ExceptionHandler({NoSuchElementException.class, EmptyResultDataAccessException.class}) // 예외처리 메소드 선언
    @ResponseStatus(HttpStatus.EXPECTATION_FAILED) // 예외처리가 제대로 되지 않은 경우
    public ResponseEntity<Map<String, String>> handleNoSuchElementException(Exception e) {
        log.error(e);
        Map<String, String> errMap = new HashMap<>();
        errMap.put("time", System.currentTimeMillis()+""); // 에러 이름, 메세지를 map에 저장
        errMap.put("msg", "No Such Element Exception"); // 에러 이름, 메세지를 map에 저장
        return ResponseEntity.badRequest().body(errMap);
    }
}
