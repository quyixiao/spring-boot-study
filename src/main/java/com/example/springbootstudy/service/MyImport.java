package com.example.springbootstudy.service;

import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Import({AutoConfigureBeforeA.class, AutoConfigureBeforeC.class})
public @interface MyImport {



}