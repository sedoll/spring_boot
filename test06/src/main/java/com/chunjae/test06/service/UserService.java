package com.chunjae.test06.service;

import com.chunjae.test06.entity.UserInfo;
import com.chunjae.test06.persistence.UserMapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserMapper userMapper;
    private BCryptPasswordEncoder pwEncoder = new BCryptPasswordEncoder();

    // 회원 목록 보기
    public List<UserInfo> userList() {return userMapper.userList();}

    // 회원 상세 정보 보기
    public UserInfo getUser(String id) {return userMapper.getUser(id);}

    // 이메일로 회원 상세 정보 보기
    public UserInfo getUserEmail(String email) {return userMapper.getUserEmail(email);}

    // no로 회원 상세 정보 보기
    public UserInfo getUserNo(Integer no) {return userMapper.getUserNo(no);}

    // 회원탈퇴
    public void withUserAct(String id) {userMapper.withUserAct(id);}

    // 휴면 처리
    public void dorUserAct(String id) {userMapper.dorUserAct(id);}

    // 계정 활성화
    public void joinUserAct(String id) {userMapper.joinUserAct(id);}

    // 계정 삭제
    public int delUser(String id) {return userMapper.delUser(id);}

    // 이메일 로그인
    public Boolean loginEmail(UserInfo userInfo) {
        UserInfo userInfo2 = userMapper.loginEmail(userInfo.getEmail());
        boolean result = false;
        try{
            boolean check = pwEncoder.matches(userInfo.getPw(), userInfo2.getPw()); // 입력된 비밀번호와 db의 암호화된 비밀번호 비교
            if(check) { // 비밀번호 일치
                result = true;
            }
        } catch (Exception e) {

        }
        return result;
    }

    // 아이디 로그인
    public Boolean loginId(UserInfo userInfo) {
        UserInfo userInfo2 = userMapper.loginId(userInfo.getId());
        boolean result = false;
        try{
            boolean check = pwEncoder.matches(userInfo.getPw(), userInfo2.getPw()); // 입력된 비밀번호와 db의 암호화된 비밀번호 비교
            if(check) { // 비밀번호 일치
                result = true;
            }
        } catch (Exception e) {

        }
        return result;
    }

    // 아이디 찾기
    public UserInfo findById(UserInfo userInfo) {return userMapper.findById(userInfo);}

    // 비밀번호 찾기
    public UserInfo findByPw(UserInfo userInfo) {return userMapper.findByPw(userInfo);}

    // 회원 수정
    public int updUser(UserInfo userInfo) {
        String ppw = userInfo.getPw();
        System.out.println(ppw);
        if(ppw.length() <= 16) {
            String pw = pwEncoder.encode(ppw);
            System.out.println(pw);
            userInfo.setPw(pw);
        }
        return userMapper.updUser(userInfo);
    }

    // 회원 가입
    public int insUser(UserInfo userInfo) {
        String pw = pwEncoder.encode(userInfo.getPw());
        userInfo.setPw(pw);
        return userMapper.insUser(userInfo);
    }
}
