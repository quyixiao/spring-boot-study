package com.example.springbootstudy.service;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan({"com.example.springbootstudy"})
@ConditionalOnProperty(prefix = "study",name="enable",havingValue = "true")
public class HelloServiceAutoConfiguration  {

}


