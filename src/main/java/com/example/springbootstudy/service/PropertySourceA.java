package com.example.springbootstudy.service;


import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Component
@PropertySource(value = {"classpath:config/bean2.properties","classpath:config/bean1.properties","classpath:config/bean.properties"},ignoreResourceNotFound =true )
//@PropertySource(value = {"classpath:config/bean4.properties"},ignoreResourceNotFound = true)
public class PropertySourceA {




}
