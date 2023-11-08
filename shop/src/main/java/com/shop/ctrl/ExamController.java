package com.shop.ctrl;

import com.shop.biz.BookServiceImpl;
import com.shop.entity.Book;
import com.shop.entity.Sample;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Controller
@RequestMapping("/ex/*")
public class ExamController {

    @Autowired
    private BookServiceImpl bookService;

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @GetMapping("sample1")
    public String getSample1(Model model) {
        Sample sample = new Sample("p1", "p2", 1234, 567800000000000000L);
        List<String> names = Arrays.asList("kim", "lee", "park", "oh", "choi");
        model.addAttribute("sample", sample);
        model.addAttribute("names",names);
        return "exam/sample1";
    }

    @GetMapping("sample2")
    public String getSample2(Model model) {
        // Map
        Map<String, String> map = new HashMap<>();
        map.put("k", "KIM");
        map.put("l", "LEE");
        map.put("p", "PARK");
        map.put("s", "SHIN");

        // List
        // 랜덤으로 1~10까지 데이터를 집어넣음 (Name1~Name10)
        List<String> list = IntStream.range(1,10).mapToObj(i -> "Name"+i).collect(Collectors.toList()); 

        // Set
        Set<String> set = new HashSet<>();
        set.add("OH");
        set.add("HAN");
        set.add("SEO");
        set.add("LEE");
        set.add("HWANG");
        set.add("HAN");

        model.addAttribute("map",map);
        model.addAttribute("list", list);
        model.addAttribute("set", set);
        return "exam/sample2";
    }

    @GetMapping("sample3")
    public String getSample3(Model model) {
        String[] cate = new String[]{"com", "pro", "ser"};
        model.addAttribute("cate",cate);
        return "exam/sample3";
    }

    @GetMapping("sample4")
    public String getSample4(Model model) {
        String[] cate = new String[]{"com", "pro", "ser"};
        model.addAttribute("cate",cate);
        return "exam/sample4";
    }

    @GetMapping("sample5")
    public String getSample5(Model model, Book book) {
        String[] cate = new String[]{"com", "pro", "ser"};
        model.addAttribute("cate",cate);
        return "exam/sample5";
    }

    @PostMapping("test5")
    public String checkBook2(@Valid Book book, BindingResult result, Model model) throws Exception {
        if(result.hasErrors()) { // entity에 정한 규칙에 맞지 않으면 error 반환
            return "exam/sample5 :: #form"; // form으로 데이터 전송
        }
        int cnt = bookService.createBook(book);
        List<Book> bookList = bookService.readBookAll();
        logger.info(bookList.toString());
        model.addAttribute("bookList", bookList);
        return "exam/sample5 :: #list";
    }

}
