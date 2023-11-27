package com.chunjae.jpa1.dto;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.java.Log;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BoardDTO {
    private Long seq;

    @NotEmpty
    @Size(min=3, max=500)
    private String title;

    @NotEmpty
    @Size(min=3, max=2000)
    private String content;

    @NotEmpty
    @Size(max=50)
    private String writer;

    private LocalDateTime regDate;
    private LocalDateTime midDate;

    //첨부파일의 이름들
    private List<String> fileNames;
}
