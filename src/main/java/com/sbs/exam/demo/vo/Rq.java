package com.sbs.exam.demo.vo;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

import com.sbs.exam.demo.Service.MemberService;
import com.sbs.exam.demo.util.Ut;

import lombok.Getter;
@Component
@Scope(value="request", proxyMode = ScopedProxyMode.TARGET_CLASS)
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
		this.req.setAttribute("rq", this);
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

	public String jsHistoryBack(String msg) {
		
		return Ut.jsHistoryBack(msg);
	}

	public String jsReplace(String msg,String uri) {
		
		return Ut.jsReplace(msg, uri);
	}
	//Rq 객체가 자연스럽게 생성되도록 유도하는 메서드
	//지우면 안됨
	//편의성을 높이기 위해 BeforeActionInterceptor 에서 호출 필요
	public void initOnBeforeActionInterceptor() {
		
	}
}
