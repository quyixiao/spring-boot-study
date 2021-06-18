package com.example.springbootstudy.service;

import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Service;

//@Service
@Configuration
public class AutoConfigureAfterC {

    public AutoConfigureAfterC() {
        System.out.println("AutoConfigureAfterC实例化");
    }
}
