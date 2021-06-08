package com.example.springbootstudy.service.impl;

import com.example.springbootstudy.service.HelloService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class HelloServiceImpl implements HelloService {


    @Value("${study.testStr}")
    private String testStr;

    @Override
    public String sayHello() {

        System.out.println(testStr);
        return "hello !!";
    }

    public String getTestStr() {
        return testStr;
    }

    public void setTestStr(String testStr) {
        this.testStr = testStr;
    }
}
