package com.example.springbootstudy.service.impl;

import com.example.springbootstudy.aop.AopService;
import com.example.springbootstudy.service.HelloService;
import com.example.springbootstudy.service.MyService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class HelloServiceImpl implements HelloService {


    @Value("${study.testStr}")
    private String testStr;

    @Override
    @AopService
    public String sayHello() {

        System.out.println(testStr);
        return testStr;
    }

    public String getTestStr() {
        return testStr;
    }

    public void setTestStr(String testStr) {
        this.testStr = testStr;
    }
}
