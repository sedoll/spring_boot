package com.pro06.dto;

import lombok.Data;

@Data
public class LectureDto {
    private Integer no;         // 강의 번호
    private Integer cno;        // 강좌 번호
    private String id;          // 작성자(관리자)
    private String title;       // 강의 제목
    private String content;     // 강의 설명
    private String keyword;     // 키워드
}
