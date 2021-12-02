package com.sbs.exam.demo.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import com.sbs.exam.demo.Service.MemberService;
import com.sbs.exam.demo.vo.Rq;
@Component
public class BeforeActionInterceptor implements HandlerInterceptor {
	@Autowired
	private MemberService memberService;
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {

		Rq rq = new Rq(request,response,memberService);
		request.setAttribute("rq",rq);
		System.out.println("Rq 객체 생성 후 저장 완료");
		
			
		return HandlerInterceptor.super.preHandle(request, response, handler);
	}

}
