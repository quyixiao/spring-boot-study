package com.example.springbootstudy.service;


import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingClass;
import org.springframework.stereotype.Service;

@Service("conditionalOnMissingClassUser")
@ConditionalOnMissingClass({"com.example.springbootstudy.service.ConditionalOnMissingClassAnnocation4",
        "com.example.springbootstudy.service.ConditionalOnMissingClassAnnocation3"})
public class ConditionalOnMissingClassUser {



}
