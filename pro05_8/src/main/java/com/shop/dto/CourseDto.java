package com.shop.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CourseDto {
    private Integer no;         // 강좌 번호
    
    private String id;          // 작성자
    
    private String cate;        // 과목
    
    private String title;       // 제목
    
    private String content;     // 설명, 내용
    
    private String cnt;         // 조회수
    
    private String resdate;     // 게시일
    
    private String ino;         // 강사 코드
    
    private Integer lec;        // 현재 수강 인원
    
    private Integer lec_max;    // 최대 수강 인원
}
