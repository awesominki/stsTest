package com.kosta.myapp;

import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.kosta.myapp.repository.MemberRepository;
import com.kosta.myapp.vo.MemberDTO;
import com.kosta.myapp.vo.MemberRoleEnum;

@SpringBootTest
public class MemberTest {
	
	@Autowired
	MemberRepository mRepo;
	
	@Test
	public void jpqlTest1() {
		//List<Object[]> plist = mRepo.getMemberWithProfileCount1("id");
		List<Object[]> plist = mRepo.getMemberWithProfileCount2("id");
		plist.forEach(objArray -> {
			System.out.println(Arrays.toString(objArray));
		});
	}
	
	//@Test
	public void delete() {
		mRepo.deleteById("id5");
	}
	
	//@Test
	public void selectById() {
		mRepo.findById("id1").ifPresentOrElse(originMember->{
			System.out.println(originMember);
		}, ()->{
			System.out.println("존재하지 않는 멤버입니다.");
		});
	}
	
	//@Test
	public void update() {
		mRepo.findById("id1").ifPresentOrElse(originMember->{
			originMember.setMname("홍길동");
			originMember.setMpassword("123123");
			originMember.setMrole(MemberRoleEnum.MANAGER);
			mRepo.save(originMember);
		}, ()->{
			System.out.println("존재하지 않는 멤버입니다.");
		});
	}
	
	//@Test
	public void selectAll() {
		mRepo.findAll().forEach(m->{
			System.out.println(m);
		});
	}
	
	//@Test
	public void insert() {
		IntStream.rangeClosed(1, 5).forEach(i->{
			MemberDTO member = MemberDTO.builder()
					.mid("id" + i)
					.mname("멤버"+i)
					.mpassword("1234")
					.mrole(i%2==0?MemberRoleEnum.ADMIN:MemberRoleEnum.USER)
					.build();
			mRepo.save(member);
		});
	}
}
