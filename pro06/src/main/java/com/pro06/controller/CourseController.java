package com.pro06.controller;

import com.pro06.entity.Course;
import com.pro06.entity.User;
import com.pro06.service.CourseService;
import com.pro06.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping("/course/*")
public class CourseController {

    @Autowired
    private CourseService courseService;

    @Autowired
    private UserService userService;

    @GetMapping("list")
    public String courseList(Model model) {
        List<Course> courseList = courseService.courseList();
        model.addAttribute("courseList", courseList);
        return "course/courseList";
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
