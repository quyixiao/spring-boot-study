package com.example.springbootstudy.service;

import com.example.springbootstudy.service.impl.IntelligentCar;
import com.example.springbootstudy.service.impl.MyCar;
import org.springframework.aop.framework.ProxyFactoryBean;
import org.springframework.aop.scope.ScopedObject;
import org.springframework.aop.support.DefaultIntroductionAdvisor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.web.context.WebApplicationContext;

@Configuration
public class ScopedProxyModeConfig {

/*

    @Bean
    @Scope(proxyMode = ScopedProxyMode.TARGET_CLASS)
    public ScopedProxyModeA scopedProxyModeA() {
        return new ScopedProxyModeA();
    }


    @Bean
    @Scope(proxyMode = ScopedProxyMode.INTERFACES)
    public IScopedProxyModeA scopedProxyModeAAA() {
        return new IScopedProxyModeA() {
            @Override
            public void aaaaaa() {
                System.out.println("--------------aaaaaaaaaaaaaa");
            }
        };
    }


    @Bean
    @Scope(proxyMode = ScopedProxyMode.INTERFACES)
    public ScopedObject scopedObject() {

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
*/


   /* @Bean
    public IntelligentCar intelligentCar() {
        return new IntelligentCar();
    }

    @Bean
    @Scope(proxyMode = ScopedProxyMode.INTERFACES)
    public DefaultIntroductionAdvisor introduceAdvisor() {
        return new DefaultIntroductionAdvisor(intelligentCar());
    }


    @Bean
    public MyCar carTarget() {
        return new MyCar();
    }

    @Bean
    public ProxyFactoryBean myCar() {
        ProxyFactoryBean myCar = new ProxyFactoryBean();
        myCar.setTarget(carTarget());
        myCar.setInterceptorNames("introduceAdvisor");
        myCar.setProxyTargetClass(true);
        return myCar;
    }*/


    public static void main(String[] args) {

        System.out.println(ScopedObject.class.isAssignableFrom(ScopedObject.class));
    }
}
