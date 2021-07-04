package com.example.springbootstudy.listener;

import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;

public class MySimpleMessageListenerContainer extends SimpleMessageListenerContainer {

    @Override
    protected void doInitialize() throws Exception {
        super.doInitialize();
        setAfterReceivePostProcessors(new MyMessagePostProcessor());
    }
}
