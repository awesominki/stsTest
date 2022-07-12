package com.kosta.myapp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kosta.myapp.repository.WebBoardRepository;
import com.kosta.myapp.repository.WebReplyRepository;
import com.kosta.myapp.vo.relation.WebBoard;
import com.kosta.myapp.vo.relation.WebReply;

@RestController
@RequestMapping("/replies/*")
public class WebReplyRestController {
	
	@Autowired
	WebReplyRepository replyRepo;
	
	@Autowired
	WebBoardRepository boardRepo;
	
	@GetMapping("/{bno}")
	public List<WebReply> replyList(@PathVariable Long bno){
		WebBoard board = WebBoard.builder().bno(bno).title("").build();
		return replyRepo.findByBoard(board);
	}
	
	@PostMapping("/{bno}")
	public List<WebReply> insert(@PathVariable Long bno,@RequestBody WebReply reply){
		WebBoard board = boardRepo.findById(bno).get();
		reply.setBoard(board);
		replyRepo.save(reply);
		return replyRepo.getRepliesOfBoard2(bno);
	}
	
	@PutMapping("/{bno}")
	public List<WebReply> update(@PathVariable Long bno,@RequestBody WebReply reply){
		WebBoard board = boardRepo.findById(bno).get();
		reply.setBoard(board);
		replyRepo.save(reply);
		return replyRepo.getRepliesOfBoard2(bno);
	}
	
	@Transactional
	@DeleteMapping("/{bno}/{rno}")
	public List<WebReply> delete(@PathVariable Long bno,@PathVariable Long rno){
		replyRepo.replyDelete(rno);
		return replyRepo.getRepliesOfBoard2(bno);
	}
}
