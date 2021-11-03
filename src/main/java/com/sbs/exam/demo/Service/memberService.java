package com.sbs.exam.demo.Service;

import org.springframework.stereotype.Service;

import com.sbs.exam.demo.repository.memberRepository;
import com.sbs.exam.demo.util.Ut;
import com.sbs.exam.demo.vo.Member;
import com.sbs.exam.demo.vo.ResultData;
import com.sbs.exam.demo.vo.ResultData;

@Service
public class memberService {

	private memberRepository memberRepository;

	public memberService(memberRepository memberRepository) {

		this.memberRepository = memberRepository;
	}

	public ResultData doJoin(String loginId, String loginPw, String name, String nickname, String cellphoneNo,
			String email) {
		Member oldMember = getMemberloginId(loginId);
		if(oldMember != null) {
			return ResultData.from("F-7", Ut.f("(%s)은(는)사용중인 아이디 입니다.", loginId));
		}
		oldMember = getMemberloginIdANDemail(name,email);
		if(oldMember != null) {
			return ResultData.from("F-8",Ut.f("(%s)은(%s)는 사용중인 정보 입니다.", name, email));
		}
		 memberRepository.doJoin(loginId, loginPw, name, nickname, cellphoneNo, email);
		 int id = memberRepository.LastInsertId();
		 
		 return ResultData.from("S-1", Ut.f("회원가입이 완료되었습니다."),id);
	}

	public Member getMemberloginId(String loginId) {
		
		return memberRepository.getMemberloginId(loginId);
	}

	private Member getMemberloginIdANDemail(String name, String email) {

		return memberRepository.getMemberloginIdANDemail(name, email);
	}

	public Member getMember(int id) {
		
		return memberRepository.getMember(id);
	}


	}



