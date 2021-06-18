package com.example.springbootstudy.service;


import org.springframework.context.annotation.Configuration;

@Configuration
public class AutoConfigureBeforeC {

    public AutoConfigureBeforeC() {
        System.out.println("AutoConfigureBeforeC实例化");
    }

}
