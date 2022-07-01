package com.kosta.myapp;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.kosta.myapp.repository.PDSBoardRepository;
import com.kosta.myapp.vo.relation.PDSBoard;
import com.kosta.myapp.vo.relation.PDSFile;

import lombok.extern.java.Log;

@SpringBootTest
@Log
public class PDSBoardTest {
	@Autowired
	PDSBoardRepository boardRepo;
	
	@Test
	public void retrieveById() {
		Long pid=122L;
		boardRepo.findById(pid).ifPresentOrElse(b->{
			log.info("pid : " + b.getPid());
			log.info("pname : " + b.getPname());
			log.info("pwriter : " + b.getPwriter());
			log.info("files2 : " + b.getFiles2());
			log.info("files2건수 : " + b.getFiles2().size());
		}, ()->{
			log.info(pid + "존재하지 않는다.");
		});
	}
	
	//@Test
	public void retrieve() {
		boardRepo.findAll().forEach(board->{
			System.out.println(board);
		});
	}
	
	//@Test
	public void insert() {
		IntStream.rangeClosed(1, 3).forEach(i->{
			PDSBoard board = PDSBoard.builder()
					.pname("메뉴얼"+i)
					.pwriter("홍길동")
					.build();
			List<PDSFile> fileList = new ArrayList<>();
			IntStream.rangeClosed(1, 5).forEach(j->{
				PDSFile file = PDSFile.builder()
						.pdsfilename("첨부파일"+j+".doc")
						.build();
				fileList.add(file);
			});
			board.setFiles2(fileList);
			boardRepo.save(board);
		});
	}
}
