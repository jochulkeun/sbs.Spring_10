package com.sbs.exam.demo.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Member {
	private int id;
	private String loginId;
	private String loginPw;
	private String name;
	private String nickname;
	private int cellPhoneNo;
	private String email;
}
