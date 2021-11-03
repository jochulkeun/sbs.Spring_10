package com.sbs.exam.demo.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sbs.exam.demo.Service.memberService;
import com.sbs.exam.demo.util.Ut;
import com.sbs.exam.demo.vo.Member;
import com.sbs.exam.demo.vo.ResultData;

@Controller
public class UsrMemberController {
	private memberService memberService;

	public UsrMemberController(memberService memberService) {

		this.memberService = memberService;
	}

	@RequestMapping("/usr/member/doJoin")
	@ResponseBody
	public Object doJoin(String loginId, String loginPw, String name, String nickname, String cellphoneNo,
			String email) {
		if (Ut.Empty(loginId)) {
			return ResultData.from("F-1", Ut.f("loginId 를(을)를 확인해주세요"));
		}
		if (Ut.Empty(loginPw)) {
			return ResultData.from("F-2", Ut.f("loginPw 를(을)를 확인해주세요"));
		}
		if (Ut.Empty(name)) {
			return ResultData.from("F-3", Ut.f("name 를(을)를 확인해주세요"));
		}
		if (Ut.Empty(nickname)) {
			return ResultData.from("F-4", Ut.f("nickname 를(을)를 확인해주세요"));
		}
		if (Ut.Empty(cellphoneNo)) {
			return ResultData.from("F-5", Ut.f("cellphoneNo 를(을)를 확인해주세요"));
		}
		if (Ut.Empty(email)) {
			return ResultData.from("F-6", Ut.f("email 를(을)를 확인해주세요"));
		}

		int id = memberService.doJoin(loginId, loginPw, name, nickname, cellphoneNo, email);

		if (id == -1) {

			return Ut.f("(%s)은(는)사용중인 아이디 입니다.", loginId);
		}
		if (id == -2) {
			return Ut.f("(%s)은(%s)는 사용중인 정보 입니다.", name, email);
		}
		Member member = memberService.getMember(id);
		return member;
	}

}
