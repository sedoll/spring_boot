package com.shop.ctrl;

import com.shop.entity.Human;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

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

    @GetMapping("/hello2")
    public String hello2(@RequestParam("name") String name, @RequestParam("age") Integer age, Model model) {
        Human human = new Human(name, age);
        model.addAttribute("msg", "너무 조호아");
        model.addAttribute("human", human);
        return "hello2";
    }

    @GetMapping("/hello3")
    public String hello3(Human human, Model model) {
        model.addAttribute("msg", "너무 조호아");
        model.addAttribute("human", human);
        return "hello2";
    }
}
