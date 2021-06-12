package com.example.springbootstudy.service;


import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

@Service("conditionalOnSingleCandidateUserAnnocation1")
//@Primary
public class ConditionalOnSingleCandidateUserAnnocation1 implements IConditionalOnSingleCandidateUserAnnocation {

    public ConditionalOnSingleCandidateUserAnnocation1() {
        System.out.println("ConditionalOnSingleCandidateUserAnnocation1实例化");
    }
}
