package com.pro06.controller;

import com.pro06.entity.User;
import com.pro06.service.UserService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Log4j2
@Controller
public class HomeController {



    @Autowired
    private UserService userService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping("/")
    public String Home(Model model){
        return "index";
    }


    @GetMapping("/term")
    public String Term(Model model){
        return "/user/joinTerm";
    }


    @GetMapping("/login")
    public String Login(@RequestParam(value = "error", required = false) String error,
                        @RequestParam(value = "exception", required = false) String exception,
                        Model model){

        /* 에러와 예외를 모델에 담아 view resolve */
        model.addAttribute("error", error);
        model.addAttribute("exception", exception);
        return "/user/login";
    }


    @GetMapping("/join1")
    public String JoinForm(Model model){
        return "/user/join1";
    }

    @GetMapping("/join2")
    public String JoinForm2(Model model){
        return "/user/join2";
    }

    @PostMapping("/joinPro1")
    public String Join(Model model, User user){
        userService.userInsert(user);
        log.info("11ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ"+ user);
        return "redirect:/";
    }

    @GetMapping("/error")
    public String error(Model model){
        return "/user/error";
    }
}
