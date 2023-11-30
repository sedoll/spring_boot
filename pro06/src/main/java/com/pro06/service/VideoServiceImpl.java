package com.pro06.service;

import com.pro06.entity.Role;
import com.pro06.entity.Status;
import com.pro06.entity.User;
import com.pro06.entity.Video;
import com.pro06.repository.VideoRepository;
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
//    public List<String> videoList(Integer cno) {
////        return videoRepository.
//    }
}
