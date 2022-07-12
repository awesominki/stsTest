package com.kosta.myapp.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.support.RequestContextUtils;

import com.kosta.myapp.repository.WebBoardRepository;
import com.kosta.myapp.repository.WebReplyRepository;
import com.kosta.myapp.vo.PageMaker;
import com.kosta.myapp.vo.PageVO;
import com.kosta.myapp.vo.relation.WebBoard;
import com.querydsl.core.types.Predicate;

import lombok.extern.java.Log;

@Log
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
	public String boardlist(@ModelAttribute PageVO pageVO, Model model,HttpServletRequest request) {
		Pageable page = pageVO.makePaging(0, "bno");
		Predicate predicate = boardRepo.makePredicate(pageVO.getType(), pageVO.getKeyword());
		Page<WebBoard> blist = boardRepo.findAll(predicate,page);
		
		Map<String, Object> map = (Map<String, Object>)RequestContextUtils.getInputFlashMap(request);
		if(map!=null) {
			String message = (String)map.get("message");
			model.addAttribute("msg",message);
			pageVO = (PageVO)map.get("pageVO");
		}
		
		PageMaker<WebBoard> pgmaker = new PageMaker<>(blist);
		model.addAttribute("blist",pgmaker);
		return "board/boardlist";
	}
	
	@GetMapping("/view.go")
	public String view(PageVO pageVO, Long bno,Model model) {
		log.info(pageVO.toString());
		log.info("bno : " + bno);
		
		model.addAttribute("vo",boardRepo.findById(bno).get()); //BoardVO로 받기 위해서 .get()해주기!
		return "board/boardview";
	}
	
	@GetMapping("/register.go")
	public void register() {
		//board/register.html 이 defalut가 된다.
	}
	
	@PostMapping("/register.go")
	public String registerPost(WebBoard board,RedirectAttributes attr) {
		log.info(board.toString());
		WebBoard insertBoard = boardRepo.save(board);
		attr.addFlashAttribute("message",insertBoard!=null?"success":"fail");
		return "redirect:/board/boardlist.go";
	}
	
	@GetMapping("/modify.go")
	public String modify(Model model, Long bno,@ModelAttribute PageVO pageVO) {
		model.addAttribute("vo",boardRepo.findById(bno).get()); //BoardVO로 받기 위해서 .get()해주기!
		return "board/modify";
	}
	
	@PostMapping("/modify.go")
	public String modifyPost(PageVO pageVO,WebBoard board, RedirectAttributes reAttr) {
		boardRepo.findById(board.getBno()).ifPresentOrElse(b->{
			b.setContent(board.getContent());
			b.setTitle(board.getTitle());
			WebBoard updateBoard = boardRepo.save(b);
			reAttr.addFlashAttribute("message",updateBoard!=null?"success":"fail");
			reAttr.addFlashAttribute("pageVO",pageVO);
		}, ()->{
			System.out.println("수정할 데이터 없음.");
		});
		return "redirect:/board/boardlist.go";
	}
	
	@PostMapping("/delete.go")
	public String delete(WebBoard board, PageVO pageVO,RedirectAttributes reAttr) {
		boardRepo.deleteById(board.getBno());
		reAttr.addFlashAttribute("message","delete OK");
		reAttr.addFlashAttribute("pageVO",pageVO);
		
		return "redirect:/board/boardlist.go";
	}
	
}
