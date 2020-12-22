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
public class MessageSenderFanout {
    @Autowired
    private AmqpTemplate template;

    public void send(String message) {
    template.convertAndSend("fanoutExchange","",message);//参数2忽略
    }
}