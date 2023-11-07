package com.shop.ctrl;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class RootController {
    // index
    @GetMapping("/")
    public String home() {
        return "index";
    }

    // index
    @GetMapping("/hello")
    public String hello(Model model) {
        model.addAttribute("msg", "너무 조호아");
        return "hello";
    }
}
