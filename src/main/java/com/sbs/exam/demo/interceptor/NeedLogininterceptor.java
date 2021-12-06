package com.sbs.exam.demo.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import com.sbs.exam.demo.vo.Rq;

@Component
public class NeedLogininterceptor implements HandlerInterceptor {

	private Rq rq;

	public NeedLogininterceptor(Rq rq) {
		this.rq = rq;
	}

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {

		if (!rq.isLogined()) {

			rq.printHistorybackJs("로그인후 이용해 주세요!");

			return false;
		}
		return HandlerInterceptor.super.preHandle(request, response, handler);
	}

}
