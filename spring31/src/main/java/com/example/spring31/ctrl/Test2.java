package com.example.spring31.ctrl;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/test2/*")
public class Test2 {
    @GetMapping("auth")
    public String auth() {
        return "test2/auth";
    }
}
