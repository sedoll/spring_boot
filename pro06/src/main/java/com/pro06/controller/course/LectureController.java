package com.pro06.controller.course;

import com.pro06.entity.Course;
import com.pro06.entity.Lecture;
import com.pro06.service.CourseServiceImpl;
import com.pro06.service.LectureServiceImpl;
import com.pro06.service.UserService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;
import java.util.List;

// 강의 컨트롤러

@Controller
@Log4j2
@RequestMapping("/lecture/*")
public class LectureController {

    @Autowired
    private LectureServiceImpl lectureService;

    @Autowired
    private UserService userService;

    // 강의 상세
    @GetMapping("detail")
    public String lectureDetail(@RequestParam("cno") Integer cno, @RequestParam("lno") Integer lno, Model model) {
        Lecture lecture = lectureService.getLecture(cno, lno);
        log.error(lecture);
        model.addAttribute("lecture", lecture);
        return "lecture/lectureDetail";
    }
    
    // 강의 생성폼 이동
    @GetMapping("insert")
    public String lectureInsert(Principal principal, @RequestParam("cno") Integer cno, Model model) {
        String id = principal.getName();

        model.addAttribute("id", id);
        model.addAttribute("cno", cno);
        return "lecture/lectureInsert";
    }
    
    // 강의 생성
    @PostMapping("insert")
    public String lectureInsert(Lecture lecture, @RequestParam("cno") Integer cno, Model model) {
        lectureService.LectureInsert(lecture, cno);
        return "redirect:/course/detail?no=" + cno;
    }
}
