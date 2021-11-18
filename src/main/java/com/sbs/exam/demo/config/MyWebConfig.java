package com.sbs.exam.demo.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.sbs.exam.demo.interceptor.BeforeActionInterceptor;
import com.sbs.exam.demo.interceptor.NeedLogininterceptor;

@Configuration
public class MyWebConfig implements WebMvcConfigurer {

	@Autowired
	BeforeActionInterceptor beforeActionInterceptor;
	@Autowired
	NeedLogininterceptor needLogininterceptor;

	@Override
	public void addInterceptors(InterceptorRegistry registry) {

		InterceptorRegistration ir = registry.addInterceptor(beforeActionInterceptor);

		ir.addPathPatterns("/**").excludePathPatterns("/Resource/**");

		registry.addInterceptor(needLogininterceptor);
		ir.addPathPatterns("/usr/article/doAdd").addPathPatterns("/usr/article/doDelete")
				.addPathPatterns("/usr/article/doModify");

	}
}
