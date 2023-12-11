package com.test01.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BoardDto {
    private Integer bno;

    @Size(max = 200)
    @NotNull
    private String title;

    @Size(max = 2000)
    @NotNull
    private String content;

    @Size(max = 50)
    @NotNull
    private String writer;

    private LocalDateTime regDate;
    private LocalDateTime modDate;
}
