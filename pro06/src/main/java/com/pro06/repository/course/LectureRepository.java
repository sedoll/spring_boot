package com.pro06.repository.course;


import com.pro06.entity.Course;
import com.pro06.entity.Lecture;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface LectureRepository extends JpaRepository<Lecture, Integer> {
    @Query("select l from Lecture l where l.course.no = :cno")
    List<Lecture> lectureCnoList(Integer cno); // cno로 강의 리스트 정보 추출

    @Query("select l from Lecture l where l.no = :lno and l.course.no = :cno")
    Lecture videoList(@Param("cno") Integer cno, @Param("lno") Integer lno);
//    List<String> videoList(@Param("cno") Integer cno, @Param("lno") Integer lno); // cno, lno를 통해 비디오 추출
}
