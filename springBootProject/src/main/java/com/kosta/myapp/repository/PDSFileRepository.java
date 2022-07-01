package com.kosta.myapp.repository;

import org.springframework.data.repository.CrudRepository;

import com.kosta.myapp.vo.relation.PDSFile;

public interface PDSFileRepository extends CrudRepository<PDSFile, Long>{
	//1. 기본으로 제공되는 메소드만 가능

}
