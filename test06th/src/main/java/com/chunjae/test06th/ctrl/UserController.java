package com.chunjae.test06th.ctrl;

import com.chunjae.test06th.biz.UserService;
import com.chunjae.test06th.entity.Euser;
import com.chunjae.test06th.excep.NoSuchDataException;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@Slf4j
@Controller
@CrossOrigin("http://localhost:8085")
@RequestMapping("/user/*")
public class UserController {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private UserService userService;
    
    // 유저 정보 페이지 (마이페이지)
    @GetMapping("/get")
    public String getUserInfo(HttpSession session, Model model){
        String name = (String) session.getAttribute("sname");
        Euser user = userService.getUser(name);
        if(user==null){
            throw new NoSuchDataException("No Such Data");
        }
        model.addAttribute("user", user);
        return "user/get";
    }
    
//    // 로그인 창
//    @GetMapping("/login")
//    public String login(HttpSession session, Model model){
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        if (authentication instanceof AnonymousAuthenticationToken)
//            return "login";
//        return "redirect:/";
//    }
//
//    //아이디 로그인
//    @PostMapping("/loginByName")
//    public String loginByNamePro(@RequestParam("name") String name,@RequestParam("password") String password, HttpSession session, Model model){
//        Euser user = userService.getByName(name);
//        if(user!=null){
//            if(user.getPassword().equals(password)){
//                model.addAttribute("msg", "로그인 성공");
//                session.setAttribute("sname", user.getName());
//                session.setAttribute("slevel", user.getLev());
//            } else {
//                model.addAttribute("msg", "비밀번호 오류 로그인 실패");
//                session.invalidate();
//            }
//        } else {
//            model.addAttribute("msg", "해당 아이디를 가진 회원이 존재하지 않습니다.");
//            session.invalidate();
//        }
//        return "redirect:/";
//    }

    //탈퇴
    @GetMapping("/withdraw")
    public String userDel(@RequestParam("id") Integer id, Model model){
        int cnt = userService.getWithdraw(id);
        return "redirect:/";
    }

    //계정 삭제
    @GetMapping("/removeUser/{name}")
    public String removeUser(@PathVariable("name") String name, Model model){
        int cnt = userService.removeUser(name);
        if(cnt == 0){
            throw new NoSuchDataException("No Delete Process Data");
        }
        return "redirect:/";
    }

//    //회원가입 폼 로딩
//    @GetMapping("/join")
//    public String joinFormLoad(){
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        if (authentication instanceof AnonymousAuthenticationToken)
//            return "join";
//        return "redirect:/";
//    }
//
//    //회원가입 처리
//    @PostMapping("/joinPro")
//    public String joinPro(Euser euser, Model model){
//        int cnt = userService.userJoin(euser);
//        if(cnt == 0){
//            throw new NoSuchDataException("No Insert Process Data");
//        }
//        return "redirect:/";
//    }
//
//    //중복 id 검증(Ajax)
//    @PostMapping("/idCheck")
//    @ResponseBody
//    public boolean idCheckAjax(@RequestBody Euser test){
//        logger.info("**************** name :"+test.getName());
//        boolean result = false;
//        Euser user = userService.getByName(test.getName());
//        if(user!=null){
//            result = false;
//        } else {
//            result = true;
//        }
//        return result;
//    }
//
//    //중복 이메일 검증(Ajax)
//    @PostMapping("/emailCheck")
//    @ResponseBody
//    public boolean emailCheckAjax(@RequestParam("email") String email){
//        Euser user = userService.getByEmail(email);
//        if(user!=null){
//            return false;
//        } else {
//            return true;
//        }
//    }

    // 회원정보수정 폼 로딩
    @GetMapping("/updateForm")
    public String updateFormLoad(@RequestParam("name") String name, Model model){
        Euser user = userService.getUserByName(name);
        model.addAttribute("msg","회원정보를 수정하실 수 있습니다.");
        model.addAttribute("user", user);
        return "user/updateUser";
    }

    // 회원정보수정
    @PostMapping("/updateUserPro")
    public String updateUserPro(Euser user, Model model){
        Euser euser = userService.getUserById(user.getId());
        int cnt = 0;
        if(user.getPassword().equals(euser.getPassword())){
            cnt = userService.updatePasswordNoChange(user);
        } else {
            cnt = userService.updateUser(user);
        }
        if(cnt == 0){
            throw new NoSuchDataException("No Update Process Data");
        }
        model.addAttribute("msg","회원정보를 수정하였습니다.");
        return "redirect:/user/updateForm?id="+euser.getName();
    }

    // 마이 페이지
    @GetMapping("/userIndex")
    public String getIndex(Model model) {
        return "user/userIndex";
    }
}