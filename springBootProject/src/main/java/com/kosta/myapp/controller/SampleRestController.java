package com.kosta.myapp.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kosta.myapp.repository.CarRepository;
import com.kosta.myapp.vo.CarVO;

@RestController
public class SampleRestController {
	@Autowired
	CarRepository carRepo;
	
	
	@GetMapping("/carlist.do")
	public List<CarVO> getCarList() {
		List<CarVO> carlist = (List<CarVO>) carRepo.findAll();
		
		return carlist;
	}
}















