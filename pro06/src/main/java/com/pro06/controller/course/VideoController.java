package com.pro06.controller.course;

import com.pro06.service.course.VideoServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping("/video/*")
public class VideoController {

    @Autowired
    private VideoServiceImpl videoService;
    
    // 영상 재생
    @GetMapping("player")
    public String player(@RequestParam("cno") Integer cno, @RequestParam("lno") Integer lno, @RequestParam("page") Integer page, Model model) {
        List<String> videoList = videoService.videoList(cno, lno);
        model.addAttribute("cno", cno);
        model.addAttribute("lno", lno);
        model.addAttribute("total_size", videoList.size());
        model.addAttribute("page", page);
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
