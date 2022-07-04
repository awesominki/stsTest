package com.kosta.myapp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.kosta.myapp.vo.CarVO;

@Controller
public class ThymeleafTestController {
	@GetMapping("/sample1")
	public String test1(Model model) {
		model.addAttribute("score", 100);
		model.addAttribute("myname", "jeon");
		CarVO car = CarVO.builder()
				.carNO(1234L)
				.model("abc")
				.build();
		model.addAttribute("mycar", car);
		return "thymeleaf1"; //templates/thymeleaf1.html
		
	}
}
