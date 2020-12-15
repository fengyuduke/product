package com.fydk.productone.controller;

import java.util.concurrent.TimeUnit;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.fydk.productone.bean.FydkProductor;
import com.fydk.productone.common.ResponseEx;
import com.fydk.productone.service.FydkProductorService;
import com.fydk.productone.util.MD5;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

@Api(value = "登录登出",tags = "登录登出设置")
@RestController
@RequestMapping("/index")
public class IndexController extends BaseController{
	
	@Autowired
	private FydkProductorService fydkProductorService;
	
	@Autowired
	private RedisTemplate<String, Object> redisTemplate;
	
	@ApiOperation(value = "登录")
	@ApiImplicitParams({ 
		@ApiImplicitParam(name = "tel", value = "手机号", required = true, dataType = "String",example = "1"),
		@ApiImplicitParam(name = "password", value = "密码", required = false, dataType = "String",example = "1"),
			})
	@PostMapping("/login")
	public ResponseEx<Object> login(@RequestParam(required = true) String tel,
			@RequestParam(required = false) String password) {
		
		EntityWrapper<FydkProductor> wrapper = new EntityWrapper<>();
		
		wrapper.eq("tel_number", tel);
		
		if(!StringUtils.isBlank(password)) {
			wrapper.eq("password", MD5.getMD5Str(password));
		}
		
		FydkProductor entity = fydkProductorService.selectOne(wrapper);
		
		if(entity == null) {
			return ResponseEx.createError("手机号或密码错误");
		}else {
			
			String code = (int) (Math.random() * 9000 + 1000) + "";
			
			String key = tel+code;
			
			entity.setPassword(null);
			
			redisTemplate.opsForValue().set(key, entity,10,TimeUnit.MINUTES);
			return ResponseEx.createSuccess(key);
		}

	}
	
	@ApiOperation(value = "登出")
	@ApiImplicitParams({ 
		@ApiImplicitParam(name = "key", value = "key值", required = true, dataType = "String",example = "1"),
			})
	@PostMapping("/loginOut")
	public ResponseEx<Object> loginOut(@RequestParam(required = true) String key) {
		
		JSONObject entity =  (JSONObject) redisTemplate.opsForValue().get(key);
		
		if(entity != null) {
			redisTemplate.delete(key);
		}else {
			return ResponseEx.createError("key值已过期");
		}
		
		return ResponseEx.createSuccess();

	}
	
	public static void main(String[] args) {
		String code = (int) (Math.random() * 9000 + 1000) + "";
		
		System.out.println(code);
	}
}
