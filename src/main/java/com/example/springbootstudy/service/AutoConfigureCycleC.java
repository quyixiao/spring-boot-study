package com.example.springbootstudy.service;

import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.context.annotation.Configuration;

@Configuration
@AutoConfigureAfter(AutoConfigureCycleB.class)
public class AutoConfigureCycleC {
}
