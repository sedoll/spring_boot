package com.chunjae.test06th;

import com.chunjae.test06th.biz.ProductServiceImpl;
import com.chunjae.test06th.entity.FileDTO;
import com.chunjae.test06th.entity.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
@SpringBootApplication
public class Test06thApplication {

	public static void main(String[] args) {
		SpringApplication.run(Test06thApplication.class, args);
	}

	@Autowired
	private ProductServiceImpl productService;
	
	// index
	@GetMapping("/")
	public String home(Model model) throws Exception {
		List<Product> productList = productService.productList();
		List<FileDTO> fileList = new ArrayList<>();
		for (Product pro:productList) {
			FileDTO dto = productService.thmbn(pro.getNo());
			fileList.add(dto);
		}
//        log.info(fileboardList.toString());
		model.addAttribute("productList", productList);
		model.addAttribute("fileList", fileList);
		return "index";
	}
}
