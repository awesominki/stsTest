package com.kosta.myapp;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.kosta.myapp.vo.CarVO;

@SpringBootTest
class SpringBootProjectApplicationTests {

	@Test
	public void test() {
		CarVO car1 = new CarVO();
		car1.setModel("Bentz");
		System.out.println(car1);
		
//		CarVO car2 = new CarVO("BMW", 3000, "black");
//		System.out.println(car2);
//		System.out.println(car2.getColor());
		
		CarVO car3 = CarVO.builder()
				.model("Bentz")
				.price(10000)
				.color("skyblue")
				.build();
		System.out.println(car3.toString());
		System.out.println(car3.equals(car1));
		
	}

}
