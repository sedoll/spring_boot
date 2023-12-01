package com.pro06.controller.course;

import com.pro06.entity.Course;
import com.pro06.entity.Lecture;
import com.pro06.service.CourseServiceImpl;
import com.pro06.service.LectureServiceImpl;
import com.pro06.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping("/course/*")
public class CourseController {

    @Autowired
    private CourseServiceImpl courseService;

    @Autowired
    private LectureServiceImpl lectureService;

    @Autowired
    private UserService userService;
    
    // 강좌 목록
    @GetMapping("list")
    public String courseList(Model model) {
        List<Course> courseList = courseService.courseList();
        model.addAttribute("courseList", courseList);
        return "course/courseList";
    }
    
    // 강좌 상세
    @GetMapping("detail")
    public String courseDetail(@RequestParam("no") Integer no, Model model) {
        // 강좌 상세
        Course course = courseService.getCourse(no);
        model.addAttribute("course", course);
        
        // 강의 목록
        List<Lecture> lectureList = lectureService.lectureCnoList(no);
        model.addAttribute("lectureList", lectureList);
        return "course/courseDetail";
    }
    
    // 강좌 생성폼 이동
    @GetMapping("insert")
    public String courseInsert(Principal principal, Model model) {
        String id = principal.getName();
        model.addAttribute("id", id);
        return "course/courseInsert";
    }
    
    // 강좌 생성
    @PostMapping("insert")
    public String courseInsert(Course course, Model model) {
        courseService.courseInsert(course);
        return "redirect:/course/list";
    }
}
