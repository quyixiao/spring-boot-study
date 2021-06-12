package com.example.springbootstudy.service;


import org.mybatis.spring.boot.autoconfigure.MybatisProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "xxx.test")
public class EnableConfigurationPropertiesTest {


    public EnableConfigurationPropertiesTest() {
        System.out.println("EnableConfigurationPropertiesTest实例化");
    }

    public EnableConfigurationPropertiesTest(String userName, String password, String homeLocation) {
        this.userName = userName;
        this.password = password;
        this.homeLocation = homeLocation;
    }

    private String userName;
    private String password;
    private String homeLocation;


    public String getHomeLocation() {
        return homeLocation;
    }

    public void setHomeLocation(String homeLocation) {
        this.homeLocation = homeLocation;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
