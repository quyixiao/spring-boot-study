package com.example.springbootstudy.service;

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ConditionalOnMissingBeanConfig {

    @Bean
    @ConditionalOnMissingBean
    public ConditionalOnMissingBeanDo conditionalOnMissingBeanDo(){
        System.out.println("第一次实例化ConditionalOnMissingBeanDo");
        return new ConditionalOnMissingBeanDo();
    }

    @Bean
    @ConditionalOnMissingBean
    public ConditionalOnMissingBeanDo conditionalOnMissingBeanDo1(){
        System.out.println("第二次实例化ConditionalOnMissingBeanDo");
        return new ConditionalOnMissingBeanDo();
    }

    class ConditionalOnMissingBeanDo{

    }

}
