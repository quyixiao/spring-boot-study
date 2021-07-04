package com.example.springbootstudy.listener;

import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;

public class MySimpleRabbitListenerContainerFactory extends SimpleRabbitListenerContainerFactory {

    @Override
    protected MySimpleMessageListenerContainer createContainerInstance() {
        return new MySimpleMessageListenerContainer();
    }
    
}
