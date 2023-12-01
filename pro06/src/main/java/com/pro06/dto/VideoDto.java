package com.pro06.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class VideoDto {
    private Integer no;             // 파일번호
    private String savefolder;      // 저장위치
    private String originfile;      // 실제파일이름
    private String savefile;        // 저장파일이름
    private Long filesize;          // 파일크기

    private Integer cno;            // 강좌 번호
    private Integer lno;            // 강의 번호
}
