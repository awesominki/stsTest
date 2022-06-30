package com.kosta.myapp;

import java.util.List;
import java.util.Optional;
import java.util.stream.IntStream;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.kosta.myapp.repository.MemberProfileRepository;
import com.kosta.myapp.repository.MemberRepository;
import com.kosta.myapp.vo.MemberDTO;
import com.kosta.myapp.vo.ProfileDTO;

import lombok.extern.java.Log;

@SpringBootTest
@Log
public class ProfileTest {
	@Autowired
	MemberProfileRepository profileRepo;
	
	@Autowired
	MemberRepository memberRepo;
	
	//@Test
	public void selectByIdUpdate() {
		Long fno = 102L;
		profileRepo.findById(fno).ifPresentOrElse(profile -> {
			profile.setFname("프로파일 수정");
			profileRepo.save(profile);
		}, () -> {
			System.out.println("프로파일이 존재하지 않음.");
		});
	}
	
	//@Test
	public void selectByIdDelete() {
		Long fno = 102L;
		profileRepo.findById(fno).ifPresentOrElse(profile -> {
			profileRepo.deleteById(fno);
		}, () -> {
			System.out.println("프로파일이 존재하지 않음.");
		});
	}
	
	@Test
	public void selectByMember() {
		MemberDTO member = memberRepo.findById("id2").orElse(null);
		if(member == null) return;
		List<ProfileDTO> plist =  profileRepo.findByMember(member);
		plist.forEach(profile -> {
			log.info(profile.toString());
			log.info(profile.getMember().toString());
			log.info("----------------------------------");
		});
	}
	
	//@Test
	public void selectAll() {
		profileRepo.findAll().forEach(profile->{
			System.out.println(profile);
		});
	}
	
	//@Test
	public void insert() {
		Optional<MemberDTO> member = memberRepo.findById("id2");
		if(member == null) {
			System.out.println("존재하지 않는 멤버이다.");
			return;
		}
		MemberDTO existMember = member.get();
		IntStream.range(1, 10).forEach(i->{
			ProfileDTO profile = ProfileDTO.builder()
					.fname("face"+i+".jpg")
					.current_yn(i==1?true:false)
					.member(existMember)
					.build();
			profileRepo.save(profile);
		});
	}
}
