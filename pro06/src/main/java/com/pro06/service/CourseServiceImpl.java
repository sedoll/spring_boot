package com.pro06.service;

import com.pro06.entity.Course;
import com.pro06.entity.Role;
import com.pro06.entity.Status;
import com.pro06.entity.User;
import com.pro06.repository.CourseRepository;
import com.pro06.repository.UserRepository;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseServiceImpl {
    @Autowired
    private CourseRepository courseRepository;
    
    // 강좌 등록
    public Course courseInsert(Course course) {
        return courseRepository.save(course);
    }
    
    // 강좌 목록 불러오기
    public List<Course> courseList() {
        return courseRepository.findAll();
    }
    
    // 강좌 상세 보기
    public Course getCourse(Integer no) {return courseRepository.getReferenceById(no);}
}
