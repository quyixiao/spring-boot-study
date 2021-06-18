package com.example.springbootstudy.service;

import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.AutoConfigureOrder;
import org.springframework.context.annotation.Configuration;

//@Service
@Configuration
@AutoConfigureAfter({AutoConfigureAfterA.class, AutoConfigureAfterC.class})
public class AutoConfigureAfterB {

    public AutoConfigureAfterB() {
        System.out.println("AutoConfigureAfterB实例化");
    }
}
