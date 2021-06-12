package com.example.springbootstudy.service;

import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.context.annotation.Configuration;

//@Service
@Configuration
@AutoConfigureAfter({AutoConfigureAfterA.class, AutoConfigureAfterC.class,AutoConfigureBeforeC.class})
public class AutoConfigureAfterB {

    public AutoConfigureAfterB() {
        System.out.println("B实例化");
    }
}
