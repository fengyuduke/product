package com.fydk.productone.rabbitSender;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


//@Configuration
public class SenderConfDirect {
     @Bean
     public Queue queue() {
          return new Queue("sendMessage");
     }
}