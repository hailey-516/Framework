package com.kh.lclinic.common.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.kh.lclinic.common.interceptor.LoginInterceptor;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

		private final LoginInterceptor loginInterceptor;
		
		public WebMvcConfig(LoginInterceptor loginInterceptor) {
			this.loginInterceptor = loginInterceptor;
		}
		
		@Override
		public void addInterceptors(InterceptorRegistry registry) {
			registry.addInterceptor(loginInterceptor/*인터셉터 클래스*/)
			.addPathPatterns("/counsel");
		}
	
	
}
