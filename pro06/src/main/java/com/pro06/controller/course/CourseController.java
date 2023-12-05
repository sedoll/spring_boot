package com.pro06.controller.course;

import com.pro06.entity.Course;
import com.pro06.entity.Lecture;
import com.pro06.entity.MyCourse;
import com.pro06.service.course.CourseServiceImpl;
import com.pro06.service.course.LectureServiceImpl;
import com.pro06.service.UserService;
import com.pro06.service.course.MyCourseServiceImpl;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.IOException;
import java.io.PrintWriter;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

// 해당 컨트롤러에서 사용한 log.error들은 싹다 alert로 나중에 바꿔야함

// 강좌 컨트롤
@Controller
@Log4j2
@RequestMapping("/course/*")
public class CourseController {

    @Autowired
    private CourseServiceImpl courseService;

    @Autowired
    private LectureServiceImpl lectureService;

    @Autowired
    private MyCourseServiceImpl myCourseService;

    @Autowired
    private UserService userService;
    
    // 강좌 목록
    @GetMapping("list")
    public String courseList(Principal principal, Model model) {
        List<Course> courseList = courseService.courseList();

        List<String> checkList = new ArrayList<>();
        
        // 로그인한 사람이면 강좌마다 수강신청 했는지 안했는지 검사
        if(principal != null) {
            String id = principal.getName();
            for (Course course: courseList) {
                Integer cnt = myCourseService.getMyCourse(id, course.getNo());
                if(cnt > 0) {
                    checkList.add("y");
                } else {
                    checkList.add("n");
                }
            }
        }

        model.addAttribute("checkList", checkList);
        model.addAttribute("courseList", courseList);
        return "course/courseList";
    }
    
    // 강좌 상세
    @GetMapping("detail")
    public String courseDetail(@RequestParam("no") Integer no, Model model) throws IOException {

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

    // 강좌 수강신청
    @GetMapping("apply")
    public String courseApply(Principal principal, @RequestParam("cno") Integer cno) {

        if(principal == null) {
            log.error("로그인을 해야 수강신청을 할 수 있습니다.");
            return "redirect:/";
        }

        String id = principal.getName();

        Integer ck = myCourseService.getMyCourse(id, cno);

        if(ck > 0) {
            log.error("이미 해당 강의를 수강하고 있습니다.");
            return "redirect:/";
        }
        
        // 수강 신청 하기전에 인원이 다 찼는지 안 찼는지 확인
        Course course1 = courseService.getCourse(cno);

        if(course1.getPeo() >= course1.getPeo_max() ) {
            log.error("이미 수강생이 다 찼습니다.");
            return "redirect:/";
        }

        Course course = new Course();
        course.setNo(cno);

        MyCourse myCourse = new MyCourse();
        myCourse.setId(id);
        myCourse.setCourse(course);

        courseService.setCoursePeo(cno);
        myCourseService.myCourseInsert(myCourse);

        return "redirect:/"; // 인덱스 이동
    }
}
