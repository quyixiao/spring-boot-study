
package com.example.springbootstudy.service.impl;


import org.springframework.stereotype.Service;


public class MyCar implements Auto {
    @Override  
    public void driving() {  
        System.out.println("开车了");  
    }  
}  