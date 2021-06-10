package com.example.springbootstudy.controller;

import com.alibaba.fastjson.JSON;
import com.example.springbootstudy.entity.TestUser;
import com.example.springbootstudy.mapper.TestUserMapper;
import com.example.springbootstudy.service.HelloService;
import com.example.springbootstudy.utils.SpringContextUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @Autowired
    private HelloService helloService;

    /*@Value("${test1.profile}")
    private String profile1;*/
    @Value("${test.profile}")
    private String profile;

    @Autowired
    private TestUserMapper testUserMapper;


    @RequestMapping("/home")
    public String home() {
        //System.out.println("profile1 " + profile1 + ",profile " + profile);
        System.out.println("profile = " + profile);
        return helloService.sayHello();
    }


    @RequestMapping("/query")
    public String home(Integer pageNum, Integer pageSize) {
        if (pageNum == null) {
            ApplicationContext applicationContext = SpringContextUtils.getApplicationContext();
            Environment environment = applicationContext.getEnvironment();
            pageNum = Integer.parseInt(environment.getProperty("pageNum"));
            pageSize = Integer.parseInt(environment.getProperty("pageSize"));
            System.out.println("前端没有设置pageNum,pageSize ，使用系统默认的配置参数");
        }
        System.out.println("====pageNum==" + pageNum + ", pageSize = " + pageSize);
        return "SUCESS";

    }

    @RequestMapping("select")
    public String select(){
        TestUser testUser = testUserMapper.selectTestUserById(14l);
        System.out.println(JSON.toJSONString(testUser));
        return "SUCESS";
    }
}
