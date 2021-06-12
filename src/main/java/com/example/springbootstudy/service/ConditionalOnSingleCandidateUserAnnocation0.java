package com.example.springbootstudy.service;


import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;


@Service
@Scope("prototype")
public class ConditionalOnSingleCandidateUserAnnocation0 {

    public ConditionalOnSingleCandidateUserAnnocation0() {
        System.out.println("ConditionalOnSingleCandidateUserAnnocation0实例化");
    }
}
