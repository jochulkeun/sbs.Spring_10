package com.sbs.exam.demo.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Member {
	String loginId;
	String loginPw;
	String name;
	String nickname;
	int cellPhoneNo;
	String email;
}
