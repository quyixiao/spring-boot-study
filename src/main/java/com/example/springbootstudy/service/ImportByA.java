package com.example.springbootstudy.service;


import org.springframework.beans.factory.annotation.Autowire;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.Scope;

@Configuration
public class ImportByA {




    //@Bean(autowire = Autowire.BY_NAME)
    @Bean(initMethod="initMethod",destroyMethod = "destroyMethod")
    @Scope("prototype")
    public ImportByBCC importByBB(){
        return new ImportByBCC();
    }


    class ImportByBCC{

        private void initMethod() {
        }

        private void destroyMethod(){

        }
    }



    @Configuration
    protected static class ImportByB {

        //@Bean(autowire = Autowire.BY_NAME)
        //@Bean(initMethod="initMethod")
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
