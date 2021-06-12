package com.example.springbootstudy.service;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Service;

@Service
@EnableConfigurationProperties(EnableConfigurationPropertiesTest.class)
public class EnableConfigurationPropertiesBean {

    public EnableConfigurationPropertiesBean() {
        System.out.println("EnableConfigurationPropertiesBean实例化");
    }
}
