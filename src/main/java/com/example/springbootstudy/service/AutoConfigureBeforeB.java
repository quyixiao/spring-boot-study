package com.example.springbootstudy.service;


import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.context.annotation.Configuration;

@Configuration
@AutoConfigureBefore({AutoConfigureBeforeA.class, AutoConfigureBeforeC.class})
public class AutoConfigureBeforeB {

    public AutoConfigureBeforeB() {
        System.out.println("BeforeB实例化");
    }
}
