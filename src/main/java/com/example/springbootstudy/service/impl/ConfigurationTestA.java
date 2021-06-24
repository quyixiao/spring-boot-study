package com.example.springbootstudy.service.impl;

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import javax.sql.DataSource;
import java.lang.reflect.Constructor;

@Configuration
public class ConfigurationTestA {


    public ConfigurationTestA() {
        System.out.println("ConfigurationTestA实例化");
    }


    @Configuration
    @Import(ImportTestA.class)
    //@ConditionalOnMissingBean({ DataSource.class })
    class ConfigurationTestB {
        public ConfigurationTestB() {
            System.out.println("ConfigurationTestB 实例化");
        }
    }


    class ConfigurationTestC {
        public ConfigurationTestC() {
            System.out.println("ConfigurationTestB 实例化");
        }
    }

    @Bean
    public ConfigurationTestC configurationTestC(){
        return  new ConfigurationTestC();
    }


    public static void main(String[] args) {
        Constructor<?>[] rawCandidates = ConfigurationTestA.ConfigurationTestB.class.getDeclaredConstructors();
        for (Constructor constructor : rawCandidates) {
            Class<?>[] classes = constructor.getParameterTypes();
            for (Class c : classes) {
                System.out.println(c.getName());
            }
        }
    }
}
