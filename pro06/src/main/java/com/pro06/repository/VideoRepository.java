package com.pro06.repository;

import com.pro06.entity.Video;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VideoRepository extends JpaRepository<Video, Integer> {

//    @Query("select v.savefile from Video v where v.lno = :lno and v.cno = :cno")
    @Query("select v.savefile from Video v where v.lecture.no = :lno and v.course.no = :cno")
    List<String> videoList(@Param("cno") Integer cno, @Param("lno") Integer lno);
}
