package com.pro06.controller.course;

import com.pro06.entity.*;
import com.pro06.repository.course.VideoRepository;
import com.pro06.service.UserServiceImpl;
import com.pro06.service.course.MyVideoServiceImpl;
import com.pro06.service.course.VideoServiceImpl;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.io.PrintWriter;
import java.security.Principal;
import java.util.Arrays;
import java.util.List;

@Controller
@Log4j2
@RequestMapping("/video/*")
public class VideoController {

    @Autowired
    private VideoServiceImpl videoService;

    @Autowired
    private MyVideoServiceImpl myVideoService;

    @Autowired
    private UserServiceImpl userService;
    
    // 영상 재생
    // 아이디, 강좌번호, 강의번호, 현재페이지를 받음
    @GetMapping("player")
    public String player(Principal principal, @RequestParam("cno") Integer cno, @RequestParam("lno") Integer lno, @RequestParam("page") Integer page, Model model) {

        if(principal == null) {
            log.error("잘 못된 접근입니다.");
            return "redirect:/";
        }

        String id = principal.getName();

        User user = userService.getId(id);
        if(user.getIsStudy().equals("y")) {
            log.error("이미 다른 강의를 수강중 입니다.");
            return "redirect:/";
        }

        // 동영상 시청 시작
//        userService.updateStudyYes(id);

        Integer ck1 = myVideoService.getMyVideoCnt(id, cno, lno);
        if(ck1 == 0) { // 만약 회원의 해당 강의 영상 정보가 없으면 생성
            MyVideo myVideo = new MyVideo();
            myVideo.setId(id);  // 회원의 아이디 저장

            Lecture lecture = new Lecture();
            lecture.setNo(lno); // 강의번호 저장
            myVideo.setLecture(lecture);

            Course course = new Course();
            course.setNo(cno);  // 강좌번호 저장
            myVideo.setCourse(course);

            myVideoService.myVideoInsert(myVideo); // 데이터 생성
        }
        
        MyVideo myVideo = myVideoService.getMyVideo(id, cno, lno);
        Integer userPage = 0;
        Integer userSec = 0;
        if(myVideo != null) {
            userPage = myVideo.getPage();
            // page 검사
            if(userPage != null && userPage < page) {
                // 동영상 시청 위치 저장
                myVideo.setSec(0);
                myVideo.setPage(page);
                myVideoService.updatePageSec(myVideo);
            }
            userSec = myVideo.getSec();
        }

        List<String> videoList = videoService.videoList(cno, lno);
        model.addAttribute("cno", cno);                         // 강좌번호
        model.addAttribute("lno", lno);                         // 강의 번호
        model.addAttribute("total_size", videoList.size());     //
        model.addAttribute("user_page", userPage);              // 수강하는 사람한테 저장되있는 페이지
        model.addAttribute("page", page);                       // 현재 영상 페이지
        model.addAttribute("sec", userSec);                     // 들은 시간
        model.addAttribute("state", myVideo.getState());        // 완강 상태
        model.addAttribute("savefile", videoList.get(page));
        return "video/player";
    }
    
    // 수강 종료 (해당 강의의 모든 영상을 다 봄)
    @GetMapping("complete")
    public void summary(Principal principal, HttpServletResponse res,
                        @RequestParam("cno") Integer cno,
                        @RequestParam("lno") Integer lno,
                        @RequestParam("page") Integer page) throws IOException {

        if(principal == null) {
            log.error("잘 못된 접근입니다.");
        }

        String id = principal.getName();

        User user = userService.getId(id);
        if(user.getIsStudy().equals("y")) {
            log.error("이미 다른 강의를 수강중 입니다.");
        }

        Integer ck1 = myVideoService.getMyVideoCnt(id, cno, lno);
        if(ck1 == 0) { // 만약 회원의 해당 강의 영상 정보가 없으면 생성
            MyVideo myVideo = new MyVideo();
            myVideo.setId(id);  // 회원의 아이디 저장

            Lecture lecture = new Lecture();
            lecture.setNo(lno); // 강의번호 저장
            myVideo.setLecture(lecture);

            Course course = new Course();
            course.setNo(cno);  // 강좌번호 저장
            myVideo.setCourse(course);

            myVideoService.myVideoInsert(myVideo); // 데이터 생성
        }

        MyVideo myVideo = myVideoService.getMyVideo(id, cno, lno);
        Integer userPage = 0;
        if(myVideo != null) {
            userPage = myVideo.getPage();
            // page 검사
            if(userPage < page) {
                // 동영상 시청 위치 저장
                myVideo.setSec(0);
                myVideo.setPage(page);
                myVideo.setState("y");
                myVideoService.updatePageSec(myVideo);
            }
        }

        res.setContentType("text/html; charset=UTF-8");
        PrintWriter out = res.getWriter();
        out.println("<script>window.close();</script>");
        out.flush();
    }
    
    // 시험
    @GetMapping("test")
    public String test(){
        return "video/test";
    }
    
    // 정답 가져오기
    @PostMapping("answers")
    @ResponseBody
    public List<String> answers() {
        List<String> answers = Arrays.asList("test", "huey", "dewey", "huey", "louie");
        return answers;
    }
    
    // 동영상에서 나간 경우
    // 여기 트랜잭션 처리 안할경우 오류 발생
    @PostMapping("closeVideo")
    @Transactional
    public void closeLecture(Principal principal,
                             @RequestParam("sec") Integer sec,
                             @RequestParam("page") Integer page,
                             @RequestParam("cno") Integer cno,
                             @RequestParam("lno") Integer lno, Model model) throws Exception {

        String id = principal.getName();

        log.warn("sec : " + sec);
        log.warn("page : " + page);
        log.warn("cno : " + cno);
        log.warn("lno : " + lno);
        log.warn("id : " + id);

        // 동영상 시청 종료
        // 유저가 동영상을 시청중이지 않다는 정보를 저장
        userService.updateStudyNo(id);
        
        // 영상 시청 정보 저장
        MyVideo myVideo = new MyVideo();
        myVideo.setSec(sec);
        myVideo.setPage(page);

        Course course = new Course();
        course.setNo(cno);
        myVideo.setCourse(course);

        Lecture lecture = new Lecture();
        lecture.setNo(lno);
        myVideo.setLecture(lecture);

        myVideo.setId(id);
        
        log.warn("영상 시청 정보 수정");
        
        // 동영상 시청 위치 저장
        myVideoService.updatePageSec(myVideo);

        log.warn("영상 시청 정보 수정완료");
    }
}
