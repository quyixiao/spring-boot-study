package com.example.springbootstudy.service;

import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Service;

//@Service
@Configuration
public class AutoConfigureAfterA {

    public AutoConfigureAfterA() {
        System.out.println("A实例化");
    }
}
