package com.chunjae.test06th;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@SpringBootApplication
public class Test06thApplication {

	public static void main(String[] args) {
		SpringApplication.run(Test06thApplication.class, args);
	}

	@GetMapping("/")
	public String home() {
		return "index";
	}
}
