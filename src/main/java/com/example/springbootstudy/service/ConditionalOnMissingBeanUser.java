package com.example.springbootstudy.service;


import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.stereotype.Service;

@Service
@ConditionalOnMissingBean(value={ConditionalOnMissingBeanAnnocation.class,ConditionalOnMissingBeanAnnocation1.class},
        ignored = ConditionalOnMissingBeanAnnocation.class)
public class ConditionalOnMissingBeanUser {

}
