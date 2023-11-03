package com.chunjae.test07;

import com.chunjae.test07.entity.Human;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
@SpringBootApplication
public class Test07Application {

    public static void main(String[] args) {
        SpringApplication.run(Test07Application.class, args);
    }

    @GetMapping("/")
    public String home(Model model) {
        Human human = new Human();
        human.setName("홍길동");
        human.setAge(28);

        List<Human> humanList = new ArrayList<>();
        for(int i=1; i<11; i++) {
            Human human1 = new Human();
            human1.setName("이름"+i);
            human1.setAge(i*5);
            humanList.add(human1);
        }

        model.addAttribute("human", human);
        model.addAttribute("humanList", humanList);
        model.addAttribute("attrName", "더미이름");
        return "index";
    }
}
