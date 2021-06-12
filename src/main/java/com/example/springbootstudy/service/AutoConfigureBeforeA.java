package com.example.springbootstudy.service;


import org.springframework.context.annotation.Configuration;

@Configuration
public class AutoConfigureBeforeA {


    public AutoConfigureBeforeA() {
        System.out.println("BeforeA实例化");
    }


}
