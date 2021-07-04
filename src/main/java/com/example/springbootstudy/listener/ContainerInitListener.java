package com.example.springbootstudy.listener;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.listener.MessageListenerContainer;
import org.springframework.amqp.rabbit.listener.RabbitListenerEndpointRegistry;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.beans.BeansException;
import org.springframework.boot.context.embedded.EmbeddedServletContainerInitializedEvent;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.util.Collection;

@Slf4j
//有问题的代码
//@Component
public class ContainerInitListener implements ApplicationContextAware {

    public ApplicationContext applicationContext;

  //  @EventListener(value = {EmbeddedServletContainerInitializedEvent.class})
    public void init() {
        RabbitListenerEndpointRegistry registry = applicationContext.getBean(RabbitListenerEndpointRegistry.class);
        Collection<MessageListenerContainer> messageListenerContainers = registry.getListenerContainers();
        for(MessageListenerContainer messageListenerContainer: messageListenerContainers){
            if(messageListenerContainer instanceof SimpleMessageListenerContainer){
                MyMessagePostProcessor myMessagePostProcessor = new MyMessagePostProcessor();
                ((SimpleMessageListenerContainer) messageListenerContainer).setAfterReceivePostProcessors(myMessagePostProcessor);
            }
        }
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }
}