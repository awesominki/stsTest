package com.kosta.myapp.vo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@Builder
@Entity
@Table(name = "tbl_profile")
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "fno")
public class ProfileDTO {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	Long fno;
	String fname;
	boolean current_yn;
	
	@ManyToOne
	private MemberDTO member; //member_mid
	 
}
