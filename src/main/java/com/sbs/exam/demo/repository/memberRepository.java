package com.sbs.exam.demo.repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.sbs.exam.demo.vo.Member;
@Component
public class memberRepository {

	private List<Member> members;

	public memberRepository() {

		members = new ArrayList<>();
	}

	public List<Member> doJoin(String loginId, String loginPw, String name, String nickname, int cellPhoneNo,
			String email) {
		Member member = new Member(loginId,loginPw,name,nickname,cellPhoneNo,email);
		members.add(member);
		return members;
	}

}
