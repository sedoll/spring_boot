package com.chunjae.test06.ctrl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@Controller
@CrossOrigin("http://localhost:8085")
@RequestMapping("/board/*")
public class BoardController {

}
