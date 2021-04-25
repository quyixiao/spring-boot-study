package com.example.springbootstudy.service.impl;

import com.example.springbootstudy.service.HelloService;
import org.springframework.stereotype.Service;

@Service
public class HelloServiceImpl implements HelloService {
    @Override
    public String sayHello() {
        return "hello !!";
    }
}
