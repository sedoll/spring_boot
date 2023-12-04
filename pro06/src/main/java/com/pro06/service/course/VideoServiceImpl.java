package com.pro06.service.course;

import com.pro06.entity.Video;
import com.pro06.repository.course.VideoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VideoServiceImpl {
    @Autowired
    private VideoRepository videoRepository;

    public Video userInsert(Video video) {
        return videoRepository.save(video);
    }
    
    // 강좌 영상
    public List<String> videoList(Integer cno, Integer lno) {
        return videoRepository.videoList(cno, lno);
    }
}
