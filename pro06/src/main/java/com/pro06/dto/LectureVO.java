package com.pro06.dto;

import com.pro06.entity.LecTest;
import com.pro06.entity.Lecture;
import com.pro06.entity.Video;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class LectureVO extends BaseDto {
    private LectureDto lecture;
    private List<VideoDto> fileList;
    private LecTestDto lecTest;
    private Integer cno;
}
