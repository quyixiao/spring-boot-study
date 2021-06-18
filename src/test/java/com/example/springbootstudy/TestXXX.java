package com.example.springbootstudy;

import org.springframework.context.annotation.ConfigurationClassPostProcessor;
import org.springframework.core.Conventions;

public class TestXXX {
    public static void main(String[] args) {
        String a = Conventions.getQualifiedAttributeName(ConfigurationClassPostProcessor.class, "configurationClass");
        System.out.println(a);
    }
}
