package com.chunjae.test07.controller;

import com.chunjae.test07.biz.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    // 로그인
    @GetMapping("login")
    public ModelAndView getLoginPage() {
        ModelAndView modelAndView = new ModelAndView();
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication instanceof AnonymousAuthenticationToken)
            modelAndView.setViewName("user/login");
        return modelAndView;
    }

    // 회원가입
    @GetMapping("join")
    public ModelAndView getJoin() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("user/join");
        return modelAndView;
    }

    // 회원가입
    @PostMapping("joinPro")
    public ModelAndView joinPro() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("user/join");
        return modelAndView;
    }
}
