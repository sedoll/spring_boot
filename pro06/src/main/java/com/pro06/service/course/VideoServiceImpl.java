package com.pro06.service.course;

import com.pro06.dto.VideoDto;
import com.pro06.entity.Video;
import com.pro06.repository.course.VideoRepository;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class VideoServiceImpl {
    @Autowired
    private VideoRepository videoRepository;

    @Autowired
    private ModelMapper modelMapper;

    // 강좌 영상
    public List<String> videoList(Integer cno, Integer lno) {
        return videoRepository.videoList(cno, lno);
    }

    // 강좌, 강의에 대한 영상 정보 추출
    public List<VideoDto> getVideoFiles(Integer cno, Integer lno) {
        List<Video> video = videoRepository.getVideoFiles(cno, lno);
        List<VideoDto> dtoList = video.stream().map(video1 ->
                modelMapper.map(video1, VideoDto.class))
                .collect(Collectors.toList());
        return dtoList;
    }
}
