package com.chunjae.jpa1.ctrl;

import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@Log4j2
public class HelloController {
    @GetMapping("/hello")
    public String hello1(Model model) {
        String hello = "Hello Spring Boot JPA";
        log.info(hello);
        model.addAttribute("msg", hello);
        return "hello1";
    }

}
