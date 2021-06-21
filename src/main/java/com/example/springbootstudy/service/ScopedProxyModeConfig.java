package com.example.springbootstudy.service;

import org.springframework.aop.scope.DefaultScopedObject;
import org.springframework.aop.scope.ScopedObject;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;

import javax.xml.bind.SchemaOutputResolver;

@Configuration
public class ScopedProxyModeConfig {

/*


    @Bean
    @Scope(proxyMode=ScopedProxyMode.TARGET_CLASS)
    public IScopedProxyModeA scopedProxyModeA(){
        return new ScopedProxyModeA();
    }


*/





    @Bean
    @Scope(proxyMode=ScopedProxyMode.INTERFACES)
    public ScopedObject scopedObject(){

        return new ScopedObject() {
            @Override
            public Object getTargetObject() {
                System.out.println("getTargetObject");
                return null;
            }

            @Override
            public void removeFromScope() {
                System.out.println("------removeFromScope-");
            }

        };
    }



    public static void main(String[] args) {

        System.out.println(ScopedObject.class.isAssignableFrom(ScopedObject.class));
    }
}
