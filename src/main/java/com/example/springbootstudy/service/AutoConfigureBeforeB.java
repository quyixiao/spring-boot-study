package com.example.springbootstudy.service;


import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@AutoConfigureBefore({AutoConfigureBeforeA.class, AutoConfigureBeforeC.class,AutoConfigureAfterB.class})
//@Import({AutoConfigureBeforeA.class, AutoConfigureBeforeC.class})
//@MyImport
public class AutoConfigureBeforeB {

    public AutoConfigureBeforeB() {
        System.out.println("AutoConfigureBeforeB实例化");
    }
}
