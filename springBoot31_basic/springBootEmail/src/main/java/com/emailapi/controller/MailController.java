package com.emailapi.controller;

import com.emailapi.dto.EmailMessage;
import com.emailapi.dto.EmailPostDto;
import com.emailapi.dto.EmailResponseDto;
import com.emailapi.service.EmailService;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.apache.catalina.connector.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.UnsupportedEncodingException;

@Controller
@RequiredArgsConstructor
public class MailController {

    private final EmailService emailService;

    // 임시 비밀번호 발급
    @PostMapping("/password")
    public ResponseEntity sendPasswordMail(@RequestBody EmailPostDto emailPostDto) {
        EmailMessage emailMessage = EmailMessage.builder()
                .to(emailPostDto.getEmail())
                .subject("임시 비밀번호 발급")
                .build();
        emailService.sendMail(emailMessage, "password");
        return ResponseEntity.ok().build();
    }

    // 회원가입 이메일 인증 - 요청 시 body로 인증번호 반환하도록 작성하였음
    @PostMapping("/email")
    public ResponseEntity sendJoinMail(@RequestBody EmailPostDto emailPostDto) {
        EmailMessage emailMessage = EmailMessage.builder()
                .to(emailPostDto.getEmail())
                .subject("이메일 인증을 위한 인증 코드 발송")
                .build();
        String code = emailService.sendMail(emailMessage, "email");
        EmailResponseDto emailResponseDto = new EmailResponseDto();
        emailResponseDto.setCode(code);
        return ResponseEntity.ok(emailResponseDto);
    }

    @PostMapping("/mail/confirm")
    public String mailConfirm(Model model) {
        String code = emailService.createKey();
        model.addAttribute("code", code);
        return "mail/mail";
    }

    @PostMapping("/mail/confirm2")
    @ResponseBody
    public String mailConfirm2(String email, Model model) throws MessagingException, UnsupportedEncodingException {
        MimeMessage msg = emailService.createMessageHtml(email);
        model.addAttribute("msg", msg);
        return "mail/mail2";
    }

    @PostMapping("/mail/verifyCode")
    @ResponseBody
    public int verifyCode(@RequestParam("code") String code) {
        int result = 0;
        System.out.println("code : "+code);
        System.out.println("code match : "+ emailService.ePw.equals(code));
        if(emailService.ePw.equals(code)) {
            result =1;
        }
        return result;
    }
}
