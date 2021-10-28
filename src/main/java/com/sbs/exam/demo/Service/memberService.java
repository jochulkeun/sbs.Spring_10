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

	public int doJoin(String loginId, String loginPw, String name, String nickname, String cellphoneNo,
			String email) {
		Member oldMember = memberRepository.getMemberloginId(loginId);
		if(oldMember != null) {
			return -1;
		}
		 memberRepository.doJoin(loginId, loginPw, name, nickname, cellphoneNo, email);
		 return memberRepository.LastInsertId();
	}

	public Member getMember(int id) {
		
		return memberRepository.getMember(id);
	}


	}



