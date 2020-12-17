package com.fydk.productone.rabbitSender;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Topic模式
 * 
 * @author LuoC
 *
 */
@Component
public class MessageSenderTopic {
	@Autowired
	private AmqpTemplate template;

	public void send(String message) {
		template.convertAndSend("exchange","topic.message",message);
		template.convertAndSend("exchange","topic.messages",message+ "== #");
	}
}
