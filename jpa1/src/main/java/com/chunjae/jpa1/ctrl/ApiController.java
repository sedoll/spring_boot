package com.chunjae.jpa1.ctrl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class ApiController {

    @GetMapping("/api1")
    public String api1() throws Exception {
        return "Hello!";
    }

    @GetMapping("/api2")
    public String[] api2() throws Exception {
        String[] strings = {"kim", "lee", "park"};
        return strings;
    }
}
