package com.example.springbootstudy.service;


import org.springframework.context.annotation.Bean;


public interface ProcessInterfaceA {


    @Bean
    default ProcessInterfaceB processInterfaceB() {
        return new ProcessInterfaceB();
    }

}
