package com.example.springbootstudy.configure;

import com.example.springbootstudy.resolver.UserParamterRelover;
import org.springframework.amqp.rabbit.annotation.RabbitListenerConfigurer;
import org.springframework.amqp.rabbit.listener.RabbitListenerEndpointRegistrar;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.messaging.handler.annotation.support.DefaultMessageHandlerMethodFactory;
import org.springframework.messaging.handler.annotation.support.MessageHandlerMethodFactory;
import org.springframework.messaging.handler.invocation.HandlerMethodArgumentResolver;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;
import java.util.List;

@Component
public class MyRabbitListenerConfigurer implements RabbitListenerConfigurer {
    @Override
    public void configureRabbitListeners(RabbitListenerEndpointRegistrar registrar) {
        MessageHandlerMethodFactory messageHandlerMethodFactory = createDefaultMessageHandlerMethodFactory(registrar);
        registrar.setMessageHandlerMethodFactory(messageHandlerMethodFactory);

    }


    private MessageHandlerMethodFactory createDefaultMessageHandlerMethodFactory(RabbitListenerEndpointRegistrar registrar) {
        DefaultMessageHandlerMethodFactory defaultFactory = new DefaultMessageHandlerMethodFactory();
        defaultFactory.setBeanFactory((BeanFactory) getFieldValueByFieldName("beanFactory", registrar));
        //defaultFactory.setMessageConverter(new MyMessageConverter());
        // 会覆盖掉之前的属性
        //List<HandlerMethodArgumentResolver> customArgumentResolvers = new ArrayList<>();
        //customArgumentResolvers.add(new UserParamterRelover());
        //defaultFactory.setArgumentResolvers(customArgumentResolvers);
        defaultFactory.afterPropertiesSet();
        org.springframework.messaging.handler.invocation.HandlerMethodArgumentResolverComposite handlerMethodArgumentResolverComposite =
                (org.springframework.messaging.handler.invocation.HandlerMethodArgumentResolverComposite) getFieldValueByFieldName("argumentResolvers", defaultFactory);
        List<HandlerMethodArgumentResolver> argumentResolvers = (List<HandlerMethodArgumentResolver>) getFieldValueByFieldName("argumentResolvers", handlerMethodArgumentResolverComposite);
        argumentResolvers.add(0, new UserParamterRelover());
        return defaultFactory;
    }

    public static Object getFieldValueByFieldName(String fieldName, Object object) {
        try {
            Field field = object.getClass().getDeclaredField(fieldName);
            //设置对象的访问权限，保证对private的属性的访问
            field.setAccessible(true);
            return field.get(object);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static void setFieldValueByFieldName(String fieldName, Object object,Object value) {
        try {
            Field field = object.getClass().getDeclaredField(fieldName);
            //设置对象的访问权限，保证对private的属性的访问
            field.setAccessible(true);
            field.set(object, value);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
