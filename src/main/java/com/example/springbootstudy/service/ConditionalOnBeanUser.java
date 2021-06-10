package com.example.springbootstudy.service;


import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.stereotype.Service;


@ConditionalOnBean(annotation = MyConditionalOnBeanTest.class)
@Service
public class ConditionalOnBeanUser {

}
