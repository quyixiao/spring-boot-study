package com.example.springbootstudy.service;


import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
public class ImportByA {




    @Configuration
    protected static class ImportByB {

        @Bean
        public ImportByBB importByBB(){
            return new ImportByBB();
        }

        protected static class ImportByBB {

        }

    }

    @Configuration
    @Import(ImportByB.class)
    protected static class ImportByC {

    }



}
