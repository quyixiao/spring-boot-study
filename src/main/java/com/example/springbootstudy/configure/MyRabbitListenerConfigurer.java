package com.example.springbootstudy.configure;

import org.springframework.amqp.rabbit.annotation.RabbitListenerAnnotationBeanPostProcessor;
import org.springframework.amqp.rabbit.annotation.RabbitListenerConfigurer;
import org.springframework.amqp.rabbit.listener.RabbitListenerEndpointRegistrar;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.propertyeditors.ReaderEditor;
import org.springframework.context.annotation.Bean;
import org.springframework.messaging.handler.annotation.support.DefaultMessageHandlerMethodFactory;
import org.springframework.messaging.handler.annotation.support.MessageHandlerMethodFactory;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;

@Component
public class MyRabbitListenerConfigurer implements RabbitListenerConfigurer {
    @Override
    public void configureRabbitListeners(RabbitListenerEndpointRegistrar registrar) {
        MessageHandlerMethodFactory messageHandlerMethodFactory = createDefaultMessageHandlerMethodFactory(registrar);
        registrar.setMessageHandlerMethodFactory(messageHandlerMethodFactory);
    }


    private MessageHandlerMethodFactory createDefaultMessageHandlerMethodFactory(RabbitListenerEndpointRegistrar registrar ) {
        DefaultMessageHandlerMethodFactory defaultFactory = new DefaultMessageHandlerMethodFactory();
        defaultFactory.setBeanFactory((BeanFactory) getFieldValueByFieldName("beanFactory", registrar));
        defaultFactory.afterPropertiesSet();
        return defaultFactory;
    }

    private Object getFieldValueByFieldName(String fieldName, Object object) {
        try {
            Field field = object.getClass().getDeclaredField(fieldName);
            //设置对象的访问权限，保证对private的属性的访问
            field.setAccessible(true);
            return  field.get(object);
        } catch (Exception e) {

            return null;
        }
    }
}
