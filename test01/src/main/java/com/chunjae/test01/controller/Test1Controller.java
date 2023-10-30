package com.chunjae.test01.controller;

import com.chunjae.test01.domain.Test1;
import com.chunjae.test01.mapper.Test1Mapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/test/*")
public class Test1Controller {

    @Autowired
    private Test1Mapper test1Mapper;

    @GetMapping("testList1.do")
    @ResponseBody   // json으로 데이터 반환
    public List<Test1> testList1() {
        return test1Mapper.getList1();
    }

    @GetMapping("testList2.do")
    @ResponseBody   // json으로 데이터 반환
    public List<Test1> testList2() {
        return test1Mapper.getList2();
    }

}
