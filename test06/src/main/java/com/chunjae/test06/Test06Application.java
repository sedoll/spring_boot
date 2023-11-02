package com.chunjae.test06;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@SpringBootApplication
public class Test06Application {

    public static void main(String[] args) {
        SpringApplication.run(Test06Application.class, args);
    }

    @GetMapping("/")
    @CrossOrigin("http://localhost:8085")
    public String home() {
        return "index";
    }

}
