package com.sbs.exam.demo.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sbs.exam.demo.Service.memberService;
import com.sbs.exam.demo.util.Ut;
import com.sbs.exam.demo.vo.Member;
 
@Controller
public class UsrMemberController {
	private memberService memberService;

	public UsrMemberController(memberService memberService) {

		this.memberService = memberService;
	}

	@RequestMapping("/usr/member/doJoin")
	@ResponseBody
	public Object doJoin(String loginId, String loginPw, String name, String nickname, String cellphoneNo, String email) {
		if (Ut.Empty(loginId)) {
			return "loginId(을)를 입력해주세요.";
		}
		if (Ut.Empty(loginPw)) {
			return "loginPw(을)를 입력해주세요.";
		}
		if (Ut.Empty(name)) {
			return "name(을)를 입력해주세요.";
		}
		if (Ut.Empty(nickname)) {
			return "nickname(을)를 입력해주세요.";
		}
		if (Ut.Empty(cellphoneNo)) {
			return "cellphoneNo(을)를 입력해주세요.";
		}
		if (Ut.Empty(email)) {
			return "email(을)를 입력해주세요.";
		}
		
		
		int id = memberService.doJoin(loginId, loginPw, name, nickname, cellphoneNo, email);
		
		
		if(id == -1) {
			
			return "중복된 아이디 입니다.";
		}
		Member member = memberService.getMember(id);
		return member;
	}




}
