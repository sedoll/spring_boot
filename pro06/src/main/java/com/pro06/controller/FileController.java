package com.pro06.controller;

import com.pro06.dto.VideoDto;
import com.pro06.service.course.VideoServiceImpl;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Log4j2
public class FileController {

    @Autowired
    private VideoServiceImpl videoService;

    @GetMapping("/files/{cno}/{lno}")
    public List<VideoDto> findFiles(@PathVariable("cno") Integer cno, @PathVariable("lno") Integer lno) throws Exception {
        List<VideoDto> uploadFiles = videoService.getVideoFiles(cno, lno);
        log.warn("uploadFiles : " + uploadFiles.toString());
        return uploadFiles;
    }
}
