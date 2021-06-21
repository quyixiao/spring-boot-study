package com.example.springbootstudy;

import org.springframework.aop.framework.autoproxy.AutoProxyUtils;
import org.springframework.beans.factory.annotation.RequiredAnnotationBeanPostProcessor;
import org.springframework.core.Conventions;

public class SkipTest {

    public static void main(String[] args) {
        String SKIP_REQUIRED_CHECK_ATTRIBUTE =
                Conventions.getQualifiedAttributeName(RequiredAnnotationBeanPostProcessor.class, "skipRequiredCheck");
        System.out.println(SKIP_REQUIRED_CHECK_ATTRIBUTE);

        System.out.println(Conventions.getQualifiedAttributeName(AutoProxyUtils.class, "preserveTargetClass"));
    }
}
