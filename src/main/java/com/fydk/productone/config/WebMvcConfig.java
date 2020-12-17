package com.fydk.productone.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.fydk.productone.aspect.MyInterceptor;


@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

	@Autowired
	private MyInterceptor myInterceptor;
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/static/**").addResourceLocations("classpath:/static/");
	}

	@Override
	public void addViewControllers(ViewControllerRegistry registry) {
		 registry.addViewController("/templates/login.html").setViewName("login");
		registry.addViewController("/main").setViewName("login");
		
	}
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		String[] excludes = new String[] {"/index", "/index/login",
				 "/error","/static/**","/swagger-resources/**","/swagger-ui.html/**"
				 ,"/webjars/**","/","/csrf/**","/test/**"};
		
		registry.addInterceptor(myInterceptor).addPathPatterns("/**")
					.excludePathPatterns(excludes);
	}
}
