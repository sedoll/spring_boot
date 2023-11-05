package com.chunjae.test04;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
@Controller
@SpringBootApplication(exclude = {SecurityAutoConfiguration.class})
public class Test03Application {

    public static void main(String[] args) {SpringApplication.run(Test03Application.class, args);}
    
    // index 지정
    @GetMapping("/")
    public static String home() {return "/index"; }
}