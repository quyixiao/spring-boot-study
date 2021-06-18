package com.example.springbootstudy.service;


import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;

@Configuration
public class AutoConfigureBeforeA {


    public AutoConfigureBeforeA() {
        System.out.println("AutoConfigureBeforeA实例化");
    }


}
