package com.kosta.myapp.repository;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.kosta.myapp.vo.relation.PDSBoard;

public interface PDSBoardRepository extends PagingAndSortingRepository<PDSBoard, Long>{
	// 1. 기본으로 제공되는 메소드만 가능
}
