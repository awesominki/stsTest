package com.kosta.myapp.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.NoRepositoryBean;

import com.kosta.myapp.vo.BoardVO;

public interface BoardRepository extends CrudRepository<BoardVO, Long> {
	// 1. 기본 CRUD는 제공된다....findAll, findById, save, delete, count, exists 등등
	// 2. 규칙에 맞는 메서드 정의 추가
	// https://docs.spring.io/spring-data/jpa/docs/2.5.1/reference/html/#jpa.query-methods 규칙 찾아보기
	
	List<BoardVO> findByTitle(String title);
	List<BoardVO> findByWriter(String writer);
	List<BoardVO> findByWriterAndTitle(String writer, String title);
	List<BoardVO> findByTitleLike(String title); // 내가 값을 줄 때 %를 포함해서 줘야함
	List<BoardVO> findByTitleContaining(String title);
	List<BoardVO> findByBnoGreaterThan(Long bno); // 이 bno보다 큰것들 조회
	
}
