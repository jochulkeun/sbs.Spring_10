package com.sbs.exam.demo.Controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sbs.exam.demo.Service.MemberService;
import com.sbs.exam.demo.util.Ut;
import com.sbs.exam.demo.vo.Member;
import com.sbs.exam.demo.vo.ResultData;
import com.sbs.exam.demo.vo.Rq;

@Controller
public class UsrMemberController {
	private MemberService memberService;

	public UsrMemberController(MemberService memberService) {

		this.memberService = memberService;
	}

	@RequestMapping("/usr/member/doJoin")
	@ResponseBody
	public ResultData doJoin(String loginId, String loginPw, String name, String nickname, String cellphoneNo,
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

		ResultData joinRd = memberService.doJoin(loginId, loginPw, name, nickname, cellphoneNo, email);

		if (joinRd.isFail()) {
			return joinRd;
		}

		Member member = memberService.getMember((int) joinRd.getData1());
		return ResultData.newData(joinRd,"member", member);
	}

	@RequestMapping("/usr/member/doLogout")
	@ResponseBody
	public String doLogout(HttpServletRequest req,HttpSession httpSession) {
		
		Rq rq = (Rq)req.getAttribute("rq");
		
		if (!rq.isLogined()) {
			return rq.jsHistoryBack("이미 로그아웃 되어있습니다.");
		}

		rq.logout();

		return rq.jsReplace(Ut.f("로그아웃 되었습니다."), "/");
	}
	
	@RequestMapping("/usr/member/login")
	public String showLogin() {
		
		return "/usr/member/login";
	}


	@RequestMapping("/usr/member/doLogin")
	@ResponseBody
	public String doLogin(HttpSession httpSession, String loginId, String loginPw) {
		boolean isLogined = false;

		if (httpSession.getAttribute("loginedMemberId") != null) {
			isLogined = true;
		}

		if (isLogined) {
			return Ut.jsHistoryBack("이미 로그인되어있습니다.");
		}

		if (Ut.Empty(loginId)) {
			return Ut.jsHistoryBack("loginId 를(을)를 확인해주세요");
		}
		if (Ut.Empty(loginPw)) {
			return Ut.jsHistoryBack("loginPw 를(을)를 확인해주세요");
		}

		Member member = memberService.getMemberloginId(loginId);
		if (member == null) {
			return Ut.jsHistoryBack("아이디를(을)를 확인해주세요");
		}
		if (member.getLoginPw().equals(loginPw) == false) {
			return Ut.jsHistoryBack("비밀번호(을)를 확인해주세요");
		}

		httpSession.setAttribute("loginedMemberId", member.getId());

		return Ut.jsReplace(Ut.f("%s님 환영합니다.", member.getNickname()),"/");
	}

}
