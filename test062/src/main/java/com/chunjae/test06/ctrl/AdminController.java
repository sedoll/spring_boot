package com.chunjae.test06.ctrl;

import com.chunjae.test06.biz.UserService;
import com.chunjae.test06.entity.Euser;
import com.chunjae.test06.excep.NoSuchDataException;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.List;

@Slf4j
@Controller
@CrossOrigin("http://localhost:8085")
@RequestMapping("/admin/*")
public class AdminController {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private UserService userService;


    @GetMapping("/userList")
    public String getUserList(Model model){
        List<Euser> userList = userService.getUserList();
        if(userList.isEmpty()){
            throw new NoSuchDataException("No Such List");
        }
        model.addAttribute("msg", "회원목록을 로딩합니다.");
        model.addAttribute("userList", userList);
        return "admin/list";
    }

    @GetMapping("/admIndex")
    public String getIndex(Model model) {
        return "admin/admIndex";
    }


    @GetMapping("/user")
    public String getUser(@RequestParam("name") String name, HttpSession session, Model model){
        Euser user = userService.getUser(name);
        if(user==null){
            throw new NoSuchDataException("No Such Data");
        }
        model.addAttribute("user", user);
        return "admin/get";
    }
}
