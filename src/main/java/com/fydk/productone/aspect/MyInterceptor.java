package com.fydk.productone.aspect;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSONObject;

@Component
public class MyInterceptor implements HandlerInterceptor {

	@Autowired
	private RedisTemplate<String, Object> redisTemplate;
	// @Resource
	// private LogService logService;

	@Override
	public void afterCompletion(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, Exception arg3)
			throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, ModelAndView arg3)
			throws Exception {
		// TODO Auto-generated method stub

	}

	// 进入 Handler方法之前执行
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		
		StringBuffer requestURL = request.getRequestURL();
		
		System.out.println("URL:"+requestURL);
		System.out.println(request);
		String key = (String)request.getParameter("key");
		if (key == null) {
			return false;
		} else {
			JSONObject jo = (JSONObject) redisTemplate.opsForValue().get(key);
			
			if(jo!= null){
				return true;
			}else {
				return false;
			}
		}
	}

}
