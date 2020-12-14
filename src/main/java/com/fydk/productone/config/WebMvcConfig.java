package com.fydk.productone.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.fydk.productone.aspect.MyInterceptor;

//@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

	@Autowired
	private MyInterceptor myInterceptor;

	public void addInterceptors2(InterceptorRegistry registry) {
		registry.addInterceptor(myInterceptor).addPathPatterns("/**").excludePathPatterns("/index", "/index/login",
				"/index/toLogin", "/error");
		addInterceptors(registry);
	}

	public void addResourceHandlers2(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/static/**").addResourceLocations("classpath:/static/");
		addResourceHandlers(registry);
	}

}
