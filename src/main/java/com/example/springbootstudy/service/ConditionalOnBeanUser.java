package com.example.springbootstudy.service;


import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.stereotype.Service;


@ConditionalOnBean(annotation = MyConditionalOnBeanTest.class)
//@ConditionalOnBean({ConditionalOnBeanAnnocation.class,ConditionalOnBeanAnnocation1.class})
@Service
public class ConditionalOnBeanUser {

}
