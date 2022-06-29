package com.kosta.myapp;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.kosta.myapp.repository.CarRepository;
import com.kosta.myapp.vo.CarVO;

@SpringBootTest
public class CarTest {

	@Autowired
	CarRepository carRepo;
	
	@Test
	public void carCount() {
		Long cnt = carRepo.count();
		System.out.println(cnt);
		
		boolean confirm = carRepo.existsById(111L); // 해당 값이 있는지 확인
		System.out.println(confirm);
	}
	
	//@Test
	public void carUpdate() {
		carRepo.findById(104L).ifPresentOrElse((car) -> {
			car.setModel("Ferrari 488");
			car.setPrice(4000000);
			car.setColor("멋쨍이 블루");
			
			carRepo.save(car);
		}, () -> {
			System.out.println("없으니까 새로 만들게");
			CarVO car = CarVO.builder()
					.model("Ferrari 488")
					.price(4000000)
					.color("깜찍이 레드")
					.build();
			
			carRepo.save(car);
		});
	}
	
	//@Test
	public void carDelete() {
		//carRepo.deleteById(102L);
		
		carRepo.findById(102L).ifPresentOrElse((car) -> {
			carRepo.delete(car);
		}, () -> {
			System.out.println("없으");
		});
	}
	
//	@ParameterizedTest
//	@ValueSource(longs = {111L, 200L, 112L, 202L})
	public void carSelectOne(Long args) {
		carRepo.findById(args).ifPresentOrElse((car) -> {
			System.out.println(car);
		}, () -> {
			System.out.println("페라리 488 끌고 다니기에는 좀 이른 나이");
		});
	}
	
	//@Test
	public void carSelectAll() {
		carRepo.findAll().forEach((car) -> {
			System.out.println(car);
		});
	}
	
	//여러개 조회 
//	@ParameterizedTest
//	@ValueSource(longs = { 102L, 103L, 999L, 1000L })
//	public void aa(Long args) {
//		carRepo.findById(args).ifPresentOrElse(car->{
//			 System.out.println(car);
//		}, ()->{System.out.println("존재하지않는다.");});
//	}
//	
//	@Test
//	public void bb() {
//		List<Long> carNos = new ArrayList<>();
//		carNos.add(102L);carNos.add(103L);
//		carNos.add(999L);carNos.add(1000L);
//		carRepo.findAllById(carNos).forEach(car->{
//			System.out.println(car);
//		});
//	}
	
	//@Test
	public void carInsert() {
		String[] arr = {"black", "white", "blue"};
		IntStream.range(480, 490).forEach((i) -> {
			CarVO car = CarVO.builder()
					.model("Ferrari" + i)
					.price(100 + i)
					.color(arr[i%3])
					.build();
			
			carRepo.save(car);
		});
	}
}

















