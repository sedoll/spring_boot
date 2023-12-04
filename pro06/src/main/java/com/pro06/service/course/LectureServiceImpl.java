package com.pro06.service.course;

import com.pro06.dto.LectureVO;
import com.pro06.entity.Course;
import com.pro06.entity.Lecture;
import com.pro06.entity.Video;
import com.pro06.repository.course.LectureRepository;
import com.pro06.repository.course.VideoRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Log4j2
public class LectureServiceImpl {
    @Autowired
    private LectureRepository lectureRepository;

    @Autowired
    private VideoRepository videoRepository;

    // 강의 정보 + 파일 등록
    public void LectureVoInsert(LectureVO vo) throws Exception {
        Lecture lecture = vo.getLecture();
        List<Video> fileList = vo.getFileList();
        
        // 강의 정보 등록
        Course course = new Course();
        course.setNo(vo.getCno());
        lecture.setCourse(course);
        lectureRepository.save(lecture);

        log.info("lecture 저장");
        
        // 강의 영상 등록
        for(Video video: fileList) {
            video.setLecture(lecture);
            video.setCourse(course);
            videoRepository.save(video);
            log.info("video 저장");
        }
    }

    // 해당강의의 강좌 목록 불러오기
    public List<Lecture> lectureCnoList(Integer cno) {
        return lectureRepository.lectureCnoList(cno);
    }

    // 강의 비디오 보기
    public Lecture getLecture(Integer cno, Integer lno) {return lectureRepository.videoList(cno, lno);}
//    public List<String> getLecture(Integer cno, Integer lno) {return lectureRepository.videoList(cno, lno);}
}
