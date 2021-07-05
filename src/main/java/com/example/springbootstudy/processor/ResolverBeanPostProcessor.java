package com.example.springbootstudy.processor;

import com.example.springbootstudy.configure.MyRabbitListenerConfigurer;
import com.example.springbootstudy.resolver.MyStandardBeanExpressionResolver;
import org.springframework.amqp.rabbit.annotation.RabbitListenerAnnotationBeanPostProcessor;
import org.springframework.amqp.rabbit.listener.RabbitListenerEndpointRegistrar;
import org.springframework.amqp.rabbit.listener.RabbitListenerEndpointRegistry;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.beans.factory.config.BeanExpressionResolver;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.context.EnvironmentAware;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

@Component
public class ResolverBeanPostProcessor implements BeanPostProcessor , BeanFactoryAware , EnvironmentAware {

    private BeanFactory beanFactory;

    public static boolean flag = true;

    public Environment environment;

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        //System.out.println("------before--------------------");
        if(flag){
            synchronized (ResolverBeanPostProcessor.class){
                if(flag){
                    if(beanFactory instanceof DefaultListableBeanFactory){
                        RabbitListenerAnnotationBeanPostProcessor beanPostProcessor =  beanFactory.getBean(RabbitListenerAnnotationBeanPostProcessor.class);
                        BeanExpressionResolver resolver = (BeanExpressionResolver) MyRabbitListenerConfigurer.getFieldValueByFieldName("resolver", beanPostProcessor);
                        String active = environment.getProperty("spring.profiles.active");
                        MyRabbitListenerConfigurer.setFieldValueByFieldName("resolver",beanPostProcessor,
                                new MyStandardBeanExpressionResolver(((DefaultListableBeanFactory) beanFactory).getBeanClassLoader(),active));

                        System.out.println("---------------");
                    }
                    flag = false;
                }
            }
        }
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        //System.out.println("--------------after------------");
        if(bean instanceof RabbitListenerEndpointRegistry){
          //  System.out.println("--------我成功了-------");
        }
        return bean;
    }


    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        System.out.println("-------------beanfactory-------");
        this.beanFactory = beanFactory;
    }

    @Override
    public void setEnvironment(Environment environment) {
        this.environment =environment;
    }
}