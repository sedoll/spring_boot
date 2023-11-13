package com.chunjae.jpa1;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@SpringBootApplication
public class Jpa1Application {

	public static void main(String[] args) {
		SpringApplication.run(Jpa1Application.class, args);
	}

	@GetMapping("/")
	public String home() throws Exception {
		return "index";
	}
}
