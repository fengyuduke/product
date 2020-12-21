package com.fydk.productone.rabbitSender;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
/**
 * Direct模式
 * @author LuoC
 *
 */
//@Component
public class MessageSenderDirect {
    @Autowired
    private AmqpTemplate template;

    public void send(String message) {
    template.convertAndSend("sendMessage",message);
    }
}