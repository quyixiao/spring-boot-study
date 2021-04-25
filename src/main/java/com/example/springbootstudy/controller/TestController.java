package com.example.springbootstudy.controller;

import com.example.springbootstudy.service.HelloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @Autowired
    private HelloService helloService;

    @RequestMapping("/home")
    public String home(){
        helloService.sayHello();
        return "hello world";
    }
}
