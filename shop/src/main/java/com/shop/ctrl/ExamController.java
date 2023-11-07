package com.shop.ctrl;

import com.shop.entity.Sample;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping("/ex/*")
public class ExamController {

    @GetMapping("sample1")
    public String getSample1(Model model) {
        Sample sample = new Sample("p1", "p2", 1234, 567800000000000000L);
        List<String> names = Arrays.asList("kim", "lee", "park", "oh", "choi");
        model.addAttribute("sample", sample);
        model.addAttribute("names",names);
        return "exam/sample1";
    }

    @GetMapping("sample2")
    public String getSample2(Model model) {
        model.addAttribute("","");
        return "exam/sample2";
    }

    @GetMapping("sample3")
    public String getSample3(Model model) {
        model.addAttribute("","");
        return "exam/sample3";
    }

}
