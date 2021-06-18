package com.example.springbootstudy.service;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.core.annotation.Order;
import org.springframework.core.type.AnnotationMetadata;
import org.springframework.core.type.StandardAnnotationMetadata;

import java.io.IOException;

@Configuration
public class BeanConfig {
    
    @Bean
    public BeanB beanB(){
        return new BeanB();
    }

    @Bean
    public BeanA beanA(){
        return new BeanA();
    }


    public static void main(String[] args) throws IOException {
        AnnotationMetadata reflectReader = new StandardAnnotationMetadata(BeanConfig.class);
        System.out.println(reflectReader.getAnnotationTypes());
    }
}
