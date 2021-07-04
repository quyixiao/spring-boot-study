package com.example.springbootstudy.listener;

import org.springframework.amqp.AmqpException;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessagePostProcessor;

public class MyMessagePostProcessor implements MessagePostProcessor {
    @Override
    public Message postProcessMessage(Message message) throws AmqpException {
        System.out.println("消息后处理器");
        String s = new String(message.getBody());
        s = " 经过消息后处理器" + s ;
        return new Message(s.getBytes(), message.getMessageProperties());
    }
}
