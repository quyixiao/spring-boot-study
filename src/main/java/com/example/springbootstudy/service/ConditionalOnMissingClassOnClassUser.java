package com.example.springbootstudy.service;


import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingClass;
import org.springframework.stereotype.Service;

@Service("conditionalOnMissingClassOnClassUser")
@ConditionalOnMissingClass({"com.example.springbootstudy.service.ConditionalOnMissingClassAnnocation4",
        "com.example.springbootstudy.service.ConditionalOnMissingClassAnnocation3"})
@ConditionalOnClass(name = "com.example.springbootstudy.service.ConditionalOnMissingClassAnnocation")
public class ConditionalOnMissingClassOnClassUser {


}
