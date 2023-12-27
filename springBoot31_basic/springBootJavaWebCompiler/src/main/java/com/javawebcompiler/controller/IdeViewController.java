package com.javawebcompiler.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class IdeViewController {
    @GetMapping(value="ide")
    public ModelAndView ideView() {
        ModelAndView mv = new ModelAndView("ide");
        return mv;
    }
}
