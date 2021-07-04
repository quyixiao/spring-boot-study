package com.example.springbootstudy.converter;

import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.converter.MessageConverter;

public class MyMessageConverter implements MessageConverter {
    @Override
    public Object fromMessage(Message<?> message, Class<?> targetClass) {
        System.out.println("from message ");
        return "hello world";
    }

    @Override
    public Message<?> toMessage(Object payload, MessageHeaders headers) {
        System.out.println("=--------tomessage =========");
        return null;
    }
}
