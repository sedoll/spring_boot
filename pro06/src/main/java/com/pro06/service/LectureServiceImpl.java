package com.pro06.service;

import com.pro06.entity.Course;
import com.pro06.entity.Lecture;
import com.pro06.entity.Video;
import com.pro06.repository.CourseRepository;
import com.pro06.repository.LectureRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LectureServiceImpl {
    @Autowired
    private LectureRepository lectureRepository;
    
    // 강의 등록
    public Lecture LectureInsert(Lecture lecture, Integer cno) {
        Course course = new Course();
        course.setNo(cno);
        lecture.setCourse(course);
        return lectureRepository.save(lecture);
    }

    // 해당강의의 강좌 목록 불러오기
    public List<Lecture> lectureCnoList(Integer cno) {
        return lectureRepository.lectureCnoList(cno);
    }

    // 강의 비디오 보기
    public Lecture getLecture(Integer cno, Integer lno) {return lectureRepository.videoList(cno, lno);}
//    public List<String> getLecture(Integer cno, Integer lno) {return lectureRepository.videoList(cno, lno);}
}
