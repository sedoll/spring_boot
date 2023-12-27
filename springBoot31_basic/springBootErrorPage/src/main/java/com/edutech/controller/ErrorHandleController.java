package com.edutech.controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.LocalDateTime;
import java.util.Date;

@Controller
public class ErrorHandleController implements ErrorController {

    //400 - 서버에서 수락이 되지 않는 요청의 문제(Bad Request)
    //401 - 미인증된 요청(Unauthorized)
    //402 - 결제요청의 오류(Payment Required)
    //403 - 불법적인 접근 또는 접근제한으로 인한 문제(Forbidden)
    //404 - 요청 URL이 없는 경우(Not Found)
    //406 - 맞지 않는 전송 방식의 요청(Not Acceptable)
    //500 - 서버 프로그램의 내부적인 오류(Internal Server Error)
    //501 - 서버에서 해당 기능을 지원하지 않음(Not Implemented)
    //502 - 게이트웨이 또는 프록시 서버의 과부하(Bad Gateway)
    //503 - 서버가 요청을 처리할 준비가 되어 있지 않은 일시적인 서비스 중단 상태(Service Unavailable)
    //504 - 시간 만료에 의한 서비스 처리 불가(Gateway Timeout)
    private final String ERROR_PAGE_PATH = "/error/error";

    @RequestMapping("/error")
    public String handleError(HttpServletRequest request, Model model){
        Object status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);
        Integer scode = Integer.valueOf(status.toString());
        LocalDateTime now = LocalDateTime.now();
        if(status != null){
            model.addAttribute("errorCode", scode);
            if(scode == HttpStatus.BAD_REQUEST.value()){    //400
                model.addAttribute("errorMsg", "서버에서 수락이 되지 않는 요청의 문제");
            } else if(scode == HttpStatus.UNAUTHORIZED.value()){    //401
                model.addAttribute("errorMsg", "미인증된 요청");
            } else if(scode == HttpStatus.PAYMENT_REQUIRED.value()){    //402
                model.addAttribute("errorMsg", "결제요청의 오류");
            } else if(scode == HttpStatus.FORBIDDEN.value()){   //403
                model.addAttribute("errorMsg", "불법적인 접근 또는 접근제한으로 인한 문제");
            } else if(scode == HttpStatus.NOT_FOUND.value()){   //404
                model.addAttribute("errorMsg", "요청 URL이 없는 경우");
            } else if(scode == HttpStatus.NOT_ACCEPTABLE.value()){  //406
                model.addAttribute("errorMsg", "맞지 않는 전송 방식의 요청");
            } else if(scode == HttpStatus.INTERNAL_SERVER_ERROR.value()){   //500
                model.addAttribute("errorMsg", "서버 프로그램의 내부적인 오류");
            } else if(scode == HttpStatus.BAD_GATEWAY.value()){   //502
                model.addAttribute("errorMsg", "게이트웨이 또는 프록시 서버의 과부하");
            } else if(scode == HttpStatus.SERVICE_UNAVAILABLE.value()){   //503
                model.addAttribute("errorMsg", "서버가 요청을 처리할 준비가 되어 있지 않은 일시적인 서비스 중단 상태");
            } else if(scode == HttpStatus.GATEWAY_TIMEOUT.value()){   //504
                model.addAttribute("errorMsg", "시간 만료에 의한 서비스 처리 불가");
            } else {
                model.addAttribute("errorMsg", "원인 불명의 오류 발생");
            }
            model.addAttribute("timestamp", now);
            return "error/error";
        }
        return "redirect:/";
    }
}
