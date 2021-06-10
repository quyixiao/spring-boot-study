package com.example.springbootstudy.service;

import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.stereotype.Service;

@ConditionalOnClass(name = {"com.example.springbootstudy.service.ConditionalOnClassAnnocation"
        ,"com.example.springbootstudy.service.ConditionalOnClassAnnocation2"})
@Service("conditionalOnClassUser")
public class ConditionalOnClassUser {


    public void a() {
        System.out.println("a");
    }

}
