package com.sbs.exam.demo.Controller;

import java.util.List;

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
	public List<Member> doJoin(String loginId, String loginPw, String name, String nickname, int cellPhoneNo,
			String email) {
		return memberService.doJoin(loginId, loginPw, name, nickname, cellPhoneNo, email);
	}
}
