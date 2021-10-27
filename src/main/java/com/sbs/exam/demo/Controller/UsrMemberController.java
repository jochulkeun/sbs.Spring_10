package com.sbs.exam.demo.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sbs.exam.demo.Service.memberService;
import com.sbs.exam.demo.vo.Member;

@Controller
public class UsrMemberController {
	private memberService memberService;

	public UsrMemberController(memberService memberService) {

		this.memberService = memberService;
	}

	@RequestMapping("/usr/member/doJoin")
	@ResponseBody
	public String doJoin(String loginId, String loginPw, String name, String nickname, int cellPhoneNo, String email) {
		memberService.doJoin(loginId, loginPw, name, nickname, cellPhoneNo, email);

		return "회원가입 되었습니다.";
	}

	@RequestMapping("/usr/member/showmember")
	@ResponseBody
	public Member showMember(int id) {
		Member member = memberService.showMember(id);
		return member;
	}
}
