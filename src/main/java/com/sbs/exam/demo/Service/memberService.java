package com.sbs.exam.demo.Service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.sbs.exam.demo.repository.memberRepository;
import com.sbs.exam.demo.vo.Member;

@Service
public class memberService {

	private memberRepository memberRepository;

	public memberService(memberRepository memberRepository) {

		this.memberRepository = memberRepository;
	}

	public void doJoin(String loginId, String loginPw, String name, String nickname, int cellPhoneNo,
			String email) {

		 memberRepository.doJoin(loginId, loginPw, name, nickname, cellPhoneNo, email);
	}

}
