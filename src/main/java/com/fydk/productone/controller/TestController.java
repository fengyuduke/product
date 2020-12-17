package com.fydk.productone.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fydk.productone.rabbitSender.MessageSenderDirect;
import com.fydk.productone.rabbitSender.MessageSenderFanout;
import com.fydk.productone.rabbitSender.MessageSenderTopic;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

@Api(value = "测试用例",tags = "测试用例")
@RestController
@RequestMapping("/test")
public class TestController {
	
	@Autowired
    private MessageSenderDirect messageSenderDirect;
	
	@Autowired
    private MessageSenderTopic messageSenderTopic;
	
	@Autowired
    private MessageSenderFanout messageSenderFanout;
	
	@ApiOperation(value = "测试接口一，发送一条信息Direct")
	@ApiImplicitParams({ 
		@ApiImplicitParam(name = "message", value = "消息", required = true, dataType = "String",example = "1"),
			})
	@PostMapping("/testDirect")
	public String testDirect(@RequestParam(required = true) String message) {
		
		System.out.println("====Direct准备发送信息 ：" + message);
		messageSenderDirect.send(message);
		
		return "发送成功";
	}
	
	@ApiOperation(value = "测试接口二，发送一条信息Topic")
	@ApiImplicitParams({ 
		@ApiImplicitParam(name = "message", value = "消息", required = true, dataType = "String",example = "1"),
			})
	@PostMapping("/testTopic")
	public String testTopic(@RequestParam(required = true) String message) {
		
		System.out.println("====Topic准备发送信息 ：" + message);
		messageSenderTopic.send(message);
		
		return "发送成功";
	}
	
	@ApiOperation(value = "测试接口三，发送一条信息Fanout")
	@ApiImplicitParams({ 
		@ApiImplicitParam(name = "message", value = "消息", required = true, dataType = "String",example = "1"),
			})
	@PostMapping("/testFanout")
	public String testFanout(@RequestParam(required = true) String message) {
		
		System.out.println("====Fanout准备发送信息 ：" + message);
		messageSenderFanout.send(message);
		
		return "发送成功";
	}
}
