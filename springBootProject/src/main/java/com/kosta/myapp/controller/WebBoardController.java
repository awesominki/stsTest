package com.kosta.myapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.kosta.myapp.repository.WebBoardRepository;
import com.kosta.myapp.repository.WebReplyRepository;

@Controller
@RequestMapping("/board/*")
public class WebBoardController {
	
	@Autowired
	WebBoardRepository boardRepo;
	
	@Autowired
	WebReplyRepository replyRepo;
	
	@GetMapping("/replyByBoard.go")
	public String replyByBoard(Long bno, Model model) {
		return "board/replyByBoard";
	}
	
	@GetMapping("/boardlist.go")
	public String boardlist(Model model) {
		model.addAttribute("blist",boardRepo.findAll());
		return "board/boardlist";
	}
}
