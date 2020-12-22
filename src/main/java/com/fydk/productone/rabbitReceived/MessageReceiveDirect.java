package com.fydk.productone.rabbitReceived;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;
/**
 * Direct模式
 * @author LuoC
 *
 */
//@Component
public class MessageReceiveDirect {

    @RabbitListener(queues="sendMessage") //监听器监听指定的Queue
    public void processC(String str) {
    	
    	System.out.println("***************收到来信1**************************");
    	
        System.out.println("Receive:"+str);
    }

}