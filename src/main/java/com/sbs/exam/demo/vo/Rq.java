package com.sbs.exam.demo.vo;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.sbs.exam.demo.Service.MemberService;
import com.sbs.exam.demo.util.Ut;

import lombok.Getter;

public class Rq {
	@Getter
	private boolean isLogined;
	@Getter
	private int loginedMemberId;
	@Getter
	private Member loginedMember;
	private HttpServletRequest req;
	private HttpServletResponse res;
	private HttpSession httpSession;

	public Rq(HttpServletRequest req, HttpServletResponse res, MemberService memberService) {
		this.httpSession = req.getSession();
		this.req = req;
		this.res = res;

		boolean isLogined = false;
		int loginedMemberId = 0;
		Member loginedMember = null;

		if (httpSession.getAttribute("loginedMemberId") != null) {
			isLogined = true;
			loginedMemberId = (int) httpSession.getAttribute("loginedMemberId");
			loginedMember = memberService.getMember(loginedMemberId);
		}
		this.isLogined = isLogined;
		this.loginedMemberId = loginedMemberId;
		this.loginedMember = loginedMember;
	}

	public void printHistorybackJs(String msg) {
		res.setContentType("text/html; charset=UTF-8");
		print(Ut.jsHistoryBack(msg));

	}

	private void print(String msg) {

		try {
			res.getWriter().append(msg);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void logout() {
		httpSession.removeAttribute("loginedMemberId");
		
	}
	public String HistoryBackOnView(String msg) {
		req.setAttribute("msg",msg);
		req.setAttribute("historyBack",true);
		
		return "common/js";
	}
}
