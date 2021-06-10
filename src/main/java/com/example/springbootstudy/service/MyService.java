package com.example.springbootstudy.service;

import org.springframework.stereotype.Service;

import java.lang.annotation.*;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Service
@Inherited
public @interface MyService {

    String value() default "";

}
