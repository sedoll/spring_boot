package com.example.spring31.ctrl;

import com.example.spring31.dto.Euser;
import com.example.spring31.excep.NoSuchDataException;
import com.example.spring31.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Controller
@CrossOrigin("http://10.41.1.127:8082")
@RequestMapping("/user/*")
public class UserController {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private UserService userService;

    //탈퇴
    @GetMapping("withdraw")
    public String userDel(@RequestParam("id") Integer id, Model model){
        int cnt = userService.getWithdraw(id);
        return "redirect:/";
    }

    //계정 삭제
    @GetMapping("removeUser/{name}")
    public String removeUser(@PathVariable("name") String name, Model model){
        int cnt = userService.removeUser(name);
        if(cnt == 0){
            throw new NoSuchDataException("No Delete Process Data");
        }
        return "redirect:/";
    }

    // 회원정보수정
    @PostMapping("updateUserPro")
    public String updateUserPro(Euser user, Model model){
        Euser euser = userService.getUserByName(user.getName());
        int cnt = 0;
        log.info(euser.toString());
        if(user.getPassword().equals(euser.getPassword())){
            cnt = userService.updatePasswordNoChange(user);
        } else {
            cnt = userService.updateUser(user);
        }
        if(cnt == 0){
            throw new NoSuchDataException("No Update Process Data");
        }
        model.addAttribute("msg","회원정보를 수정하였습니다.");
        return "redirect:mypage";
    }

}
