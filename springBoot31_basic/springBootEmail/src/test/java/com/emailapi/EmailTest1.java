package com.emailapi;

import com.emailapi.service.EmailService;
import groovy.util.logging.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.bind.annotation.RestController;

@SpringBootTest
@RestController
@Slf4j
public class EmailTest1 {

    @Autowired
    private EmailService emailService;

}
