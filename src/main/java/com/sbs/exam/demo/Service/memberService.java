package com.sbs.exam.demo.Service;

import org.springframework.stereotype.Service;

import com.sbs.exam.demo.repository.memberRepository;
import com.sbs.exam.demo.vo.Member;

@Service
public class memberService {

	private memberRepository memberRepository;

	public memberService(memberRepository memberRepository) {

		this.memberRepository = memberRepository;
	}

	public int doJoin(String loginId, String loginPw, String name, String nickname, int cellPhoneNo,
			String email) {

		 memberRepository.doJoin(loginId, loginPw, name, nickname, cellPhoneNo, email);
		 return memberRepository.LastInsertId();
	}

	public Member getMember(int id) {
		
		return memberRepository.getMember(id);
	}


	}



