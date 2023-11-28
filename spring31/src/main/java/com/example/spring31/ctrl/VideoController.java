package com.example.spring31.ctrl;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/video/*")
public class VideoController {
    @GetMapping("player")
    public String player(@RequestParam("savefile") String savefile, @RequestParam("size") Integer size, @RequestParam("page") Integer page, Model model) {
        model.addAttribute("size", size);
        model.addAttribute("page", page);
        model.addAttribute("savefile", savefile);
        return "video/player";
    }

    @PostMapping("check")
    public String check(){
        return "video/check";
    }
}
