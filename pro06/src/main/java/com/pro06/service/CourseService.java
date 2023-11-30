package com.pro06.service;

import com.pro06.entity.Course;
import org.springframework.stereotype.Service;

import java.util.List;

public interface CourseService {
    Course courseInsert(Course course); // 강좌 등록
    List<Course> courseList(); // 강좌 전체 목록
}
