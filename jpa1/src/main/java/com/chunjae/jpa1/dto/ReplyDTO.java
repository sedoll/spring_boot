package com.chunjae.jpa1.dto;

import com.chunjae.jpa1.entity.Board;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ReplyDTO {
    private Long rno;               // reply no
    // board
    @NotNull // NULL
    private Long bno;               // board no
    @NotEmpty // ''
    private String replyText;       // 답글
    @NotEmpty // ''
    private String replyer;         // 작성자
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss") // 날짜를 패턴 형식으로 변형
    private LocalDateTime regDate;
    @JsonIgnore // 무시
    private LocalDateTime midDate;
}
