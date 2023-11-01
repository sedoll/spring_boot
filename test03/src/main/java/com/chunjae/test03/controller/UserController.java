package com.chunjae.test03.controller;

import com.chunjae.test03.entity.UserInfo;
import com.chunjae.test03.service.UserService;
import com.chunjae.test03.utils.EmailSocket;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.PrintWriter;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

// GET(SELECT), POST(INSERT), DELETE(DELETE), PUT(UPDATE)

@Controller
@RequestMapping("/user/*")
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    HttpSession session; // 세션 생성
    
    // 이메일 정규식
    private static final String EMAIL_REGEX =
            "^[a-zA-Z0-9_+&*-]+(?:\\." +
                    "[a-zA-Z0-9_+&*-]+)*@" +
                    "(?:[a-zA-Z0-9-]+\\.)+[a-z" +
                    "A-Z]{2,7}$";
    
    // 아이디 정규식
    private static final String ID_REGEX = "^[a-z0-9]{5,18}$";


    // 회원정보 출력
    // 만약 테이블이 비어있으면 빈 리스트 반환
    @GetMapping("userList.do")
    @ResponseBody
    public List<UserInfo> userInfoList() throws Exception {
        List<UserInfo> userInfoList = userService.userList();
        if(userInfoList.isEmpty()) { // 데이터가 없는 경우를 위해 예외처리
            throw new NoSuchFieldException("No Such List");
        }
        return userInfoList;
    }
//
    // 특정 회원 정보 출력
    // 일치하는 데이터가 업승면 null 반환
    @GetMapping("getUser.do")
    @ResponseBody
    public UserInfo getUser(HttpServletRequest req) throws Exception {
        String id = req.getParameter("id");
        UserInfo userInfo = userService.getUser(id);
        if(userInfo==null) { // 회원이 없으면 예외처리, url로 직접 들어오는 것도 방지
            throw new NoSuchFieldException("No Such Data");
        }
        return userInfo;
    }

    // 회원정보 출력
//    @GetMapping("userList.do")
//    public String userInfoList(Model model) throws Exception{
//        List<UserInfo> userInfoList = userService.userList();
//        model.addAttribute("userList", userInfoList);
//        return "/user/list";
//    }
//
//    // 특정 회원 정보 출력
//    @GetMapping("getUser.do")
//    public String getUser(HttpServletRequest req, Model model) throws Exception{
//        String id = req.getParameter("id");
//        UserInfo userInfo = userService.getUser(id);
//        model.addAttribute("userInfo", userInfo);
//        return "/user/get";
//    }

    // 아이디 로그인 폼 이동
    @GetMapping("idLoginForm.do")
    public String idLoginForm() throws Exception{
        String id = (String) session.getAttribute("sid");
        if(id!=null) {
            return "/index";
        } else {
            return "/user/login";
        }
    }

    // 아이디 로그인
    @PostMapping("idLogin.do")
    public String idLogin(HttpServletRequest req, HttpServletResponse res) throws Exception {
        String id = req.getParameter("id");
        String pw = req.getParameter("pw");
        UserInfo userInfo = new UserInfo();
        UserInfo userInfo2;

        boolean ck = isValidEmail(id);
        boolean result = false;

        if(ck) {
            userInfo.setEmail(id);
            userInfo.setPw(pw);
            userInfo2 = userService.getUserEmail(id);
            result = userService.loginEmail(userInfo);
        } else {
            userInfo.setId(id);
            userInfo.setPw(pw);
            userInfo2 = userService.getUser(id);
            result = userService.loginId(userInfo);
        }

        System.out.println(ck);
        if(result) {
            res.setContentType("text/html;charset=UTF-8");
            PrintWriter out = res.getWriter();
            out.println("<script>alert('로그인 성공');</script>");
            out.flush();
            session.setAttribute("slev", userInfo2.getLev());
            session.setAttribute("sid", userInfo2.getId());
            return "/index";
        } else {
            res.setContentType("text/html;charset=UTF-8");
            PrintWriter out = res.getWriter();
            out.println("<script>alert('로그인 실패');</script>");
            out.flush();
            return "/user/login";
        }
    }

    // 계정 삭제 - delete 방식
    // delete 된 데이터가 없으면 0 반환
    @DeleteMapping("remove.do")
    @ResponseBody
    public String userDelete(@RequestParam String id) throws Exception {
        int cnt = userService.delUser(id);
        if(cnt == 0) {
            throw new NoSuchFieldException("Nothing to Process");
        }
        return id + " 삭제 완료, 처리 수: " + cnt;
    }

    // 계정활성화

    // 휴면 처리

    // 아이디 찾기
    
    // 비밀번호 찾기

    // 로그아웃
    @GetMapping("logout.do")
    public String logout() {
        session.invalidate();
        return "/index";
    }
    
    //회원가입
    //회원가입 폼 이동
    @GetMapping("insertForm.do")
    public String userInsertForm() throws Exception {
        return "/user/join";
    }

    //회원 가입 - 회원 가입 처리
//    @RequestMapping(value = "insert.do", method = RequestMethod.POST)
//    public String userInsert(HttpServletResponse res, UserInfo userInfo, Model model) throws Exception {
//        userService.insUser(userInfo);
//        res.setContentType("text/html;charset=UTF-8");
//        PrintWriter out = res.getWriter();
//        out.println("<script>alert('회원가입 완료');</script>");
//        out.flush();
//        return "/index";
//    }

    // 회원가입 - post json 방식
    // insert된 데이터가 없으면 0 반환
    @PostMapping("insertPost.do")
    @ResponseBody
    public UserInfo userInsertPost(UserInfo userInfo) throws Exception {
        int cnt = userService.insUser(userInfo);
        if(cnt == 0) {
            throw new NoSuchFieldException("No Insert Process Data");
        }
        return userService.getUser(userInfo.getId());
    }
    
    // 회원가입 - 중복 아이디 검사
    @RequestMapping(value = "idCheck.do", method = RequestMethod.POST)
    public void idCheck(HttpServletResponse response, HttpServletRequest request, Model model) throws Exception {
        String id = request.getParameter("id");
        boolean result = isValidId(id); // 아이디 유효성 검증 결과를 먼저 확인

        if (result) {
            UserInfo userInfo = userService.getUser(id);
            if (userInfo != null) {
                result = false; // 이미 존재하는 아이디인 경우 false로 설정
            }
        }

        JSONObject json = new JSONObject();
        json.put("result", result);
        PrintWriter out = response.getWriter();
        out.println(json.toString());
    }

    //회원정보수정
    // 회원정보 수정 폼 이동
    @GetMapping("updateForm.do")
    public String editForm(HttpServletRequest request, Model model) throws Exception {
        String id = request.getParameter("id");
        UserInfo userInfo = userService.getUser(id);
        model.addAttribute("user", userInfo);
        return "/user/mypage/userUpdate";
    }
    
    // 회원정보수정 - post
    @PostMapping("update.do")
    public String memberEdit(HttpServletRequest request, HttpServletResponse res, UserInfo userInfo, Model model) throws Exception {
        userService.updUser(userInfo);
        res.setContentType("text/html;charset=UTF-8");
        PrintWriter out = res.getWriter();
        out.println("<script>alert('수정 완료');</script>");
        out.flush();
        model.addAttribute("user", userInfo);
        return "redirect:/user/updateForm.do?id="+userInfo.getId();
    }
    
    // 회원정보수정 - put 나중에 사용할 방식
    // update된 데이터가 없으면 0 반환
    @PutMapping("update.do")
    @ResponseBody
    public UserInfo memberEditPut(UserInfo userInfo) throws Exception{
        int cnt = userService.updUser(userInfo);
        if(cnt == 0) {
            throw new NoSuchFieldException("No Update Process Data");
        }
        return userService.getUser(userInfo.getId());
    }

    
    //회원등급변경 - 관리자


    // 이메일 유효성 검증
    public static boolean isValidEmail(String email) {
        Pattern pattern = Pattern.compile(EMAIL_REGEX);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }
    
    // 아이디 유효성 검증
    public static boolean isValidId(String id) {
        Pattern pattern = Pattern.compile(ID_REGEX);
        Matcher matcher = pattern.matcher(id);
        return matcher.matches();
    }
    
    // 이메일 보내기
    public void sendMail(UserInfo userInfo) {
        EmailSocket dm = new EmailSocket();
        dm.sendMail(userInfo);
    }
}
