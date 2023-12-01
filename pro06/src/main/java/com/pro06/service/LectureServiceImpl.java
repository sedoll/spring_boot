package com.pro06.service;

import com.pro06.dto.VideoDto;
import com.pro06.dto.LectureVO;
import com.pro06.entity.Course;
import com.pro06.entity.Lecture;
import com.pro06.entity.Video;
import com.pro06.repository.LectureRepository;
import com.pro06.repository.VideoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LectureServiceImpl {
    @Autowired
    private LectureRepository lectureRepository;

    @Autowired
    private VideoRepository videoRepository;
    
    // 강의 등록
    public Lecture LectureInsert(Lecture lecture, Integer cno) {
        Course course = new Course();
        course.setNo(cno);
        lecture.setCourse(course);
        return lectureRepository.save(lecture);
    }

    // 강의 정보 + 파일 등록
    public void LectureVoInsert(LectureVO vo) throws Exception {
        Lecture lecture = vo.getLecture();
        List<Video> fileList = vo.getFileList();
        
        // 강의 정보 등록
        Course course = new Course();
        course.setNo(vo.getCno());
        lecture.setCourse(course);
        lectureRepository.save(lecture);
        
        // 강의 영상 등록
        for(Video video: fileList) {
            video.setLecture(lecture);
            video.setCourse(course);
            videoRepository.save(video);
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
