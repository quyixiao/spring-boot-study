package com.example.springbootstudy.service;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


//@Configuration
public interface ProcessInterfaceAA extends ProcessInterfaceA {


    @Bean
    default ProcessInterfaceC processInterfaceC() {
        return new ProcessInterfaceC();
    }

}
