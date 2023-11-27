package com.example.spring31.ctrl;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/test/*")
public class Test {
    @GetMapping("test1")
    public String test1() {
        return "test/test1";
    }
}
