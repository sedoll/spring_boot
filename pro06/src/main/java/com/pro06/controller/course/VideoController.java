package com.pro06.controller.course;

import com.pro06.service.course.VideoServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping("/video/*")
public class VideoController {

    @Autowired
    private VideoServiceImpl videoService;
    
    // 영상 재생
    // 아이디, 강좌번호, 강의번호, 현재페이지를 받음
    @GetMapping("player")
    public String player(Principal principal, @RequestParam("cno") Integer cno, @RequestParam("lno") Integer lno, @RequestParam("page") Integer page, Model model) {
        List<String> videoList = videoService.videoList(cno, lno);
        model.addAttribute("cno", cno);                         // 강좌번호
        model.addAttribute("lno", lno);                         // 강의 번호
        model.addAttribute("total_size", videoList.size());     //
        model.addAttribute("user_page", 0);         // 수강하는 사람한테 저장되있는 페이지
        model.addAttribute("page", page);                       // 현재 영상 페이지
        model.addAttribute("time", 10);             // 임시 데이터
        model.addAttribute("savefile", videoList.get(page));
        return "video/player";
    }
    
    // 요약정리
    @GetMapping("summary")
    public String summary(){
        return "video/summary";
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
}
