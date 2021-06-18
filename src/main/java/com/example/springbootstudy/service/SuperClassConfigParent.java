package com.example.springbootstudy.service;

import org.springframework.context.annotation.Bean;

public class SuperClassConfigParent {


    @Bean
    public SuperClassConfigA superClassConfigA(){
        return new SuperClassConfigA();
    }


}
