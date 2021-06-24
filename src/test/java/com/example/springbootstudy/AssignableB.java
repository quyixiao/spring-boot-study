package com.example.springbootstudy;

import org.springframework.boot.autoconfigure.AutoConfigurationPackages;

public class AssignableB extends AssignableA {


    public static void main(String[] args) {
        System.out.println(AssignableB.class.isAssignableFrom(AssignableA.class));
        System.out.println(AssignableA.class.isAssignableFrom(AssignableB.class));
        System.out.println(AutoConfigurationPackages.class.getName());
    }
}
