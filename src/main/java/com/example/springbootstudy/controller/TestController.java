package com.example.springbootstudy.controller;

import com.alibaba.fastjson.JSON;
import com.example.springbootstudy.entity.TestUser;
import com.example.springbootstudy.mapper.TestUserMapper;
import com.example.springbootstudy.service.*;
import com.example.springbootstudy.utils.SpringContextUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Primary;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

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


/*    @Autowired
    private ConditionalOnClassUser conditionalOnClassUser;*/

    @RequestMapping("/home")
    public String home() {
        //    conditionalOnClassUser.a();
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
    public String select() {
        TestUser testUser = testUserMapper.selectTestUserById(14l);
        System.out.println(JSON.toJSONString(testUser));
        return "SUCESS";
    }

    @RequestMapping("conditionalOnClassTest")
    public String conditionalOnClassTest() {
        Object object = SpringContextUtils.getBean("conditionalOnClassUser");
        System.out.println(object);
        return "Sucess";
    }

    @RequestMapping("conditionalOnMissingClassUserTest")
    public String conditionalOnMissingClassUserTest() {
        Object object = SpringContextUtils.getBean("conditionalOnMissingClassUser");
        System.out.println(object);
        return "Sucess";
    }


    @RequestMapping("conditionalOnMissingClassOnClassUserTest")
    public String conditionalOnMissingClassOnClassUserTest() {
        Object object = SpringContextUtils.getBean("conditionalOnMissingClassOnClassUser");
        System.out.println(object);
        System.out.println();
        return "Sucess";
    }


    @Autowired
    private ServiceTestImpl serviceTestImpl;

    @Autowired
    private ServiceTest serviceTest;


    @RequestMapping("serviceTestImplTest")
    public String serviceTestImplTest() {
        System.out.println(serviceTestImpl);
        return "Sucess";
    }

    @Autowired
    private ConditionalOnBeanUser conditionalOnBeanUser;



    @RequestMapping("conditionalOnBeanUserTest")
    public String conditionalOnBeanUserTest() {
        System.out.println(conditionalOnBeanUser);
     //   ConditionalOnBeanAnnocation1 conditionalOnBeanAnnocation1 = SpringContextUtils.getBean(ConditionalOnBeanAnnocation1.class);
//        System.out.println(conditionalOnBeanAnnocation1);
        return "Sucess";
    }


    @Autowired
    private ConditionalOnSingleCandidateUser conditionalOnSingleCandidateUser;


    @Autowired
    private IConditionalOnSingleCandidateUserAnnocation iConditionalOnSingleCandidateUserAnnocation;




    @RequestMapping("conditionalOnSingleCandidateUserTest")
    public String conditionalOnSingleCandidateUserTest() {
        System.out.println(conditionalOnSingleCandidateUser);
        ConditionalOnSingleCandidateUserAnnocation0 bean1 = SpringContextUtils.getBean(ConditionalOnSingleCandidateUserAnnocation0.class);
        ConditionalOnSingleCandidateUserAnnocation0 bean2 = SpringContextUtils.getBean(ConditionalOnSingleCandidateUserAnnocation0.class);
        System.out.println(bean2 == bean1);
        return "Sucess";
    }

    @Autowired
    private ConditionalOnMissingBeanUser conditionalOnMissingBeanUser;


    @RequestMapping("conditionalOnMissingBeanUserTest")
    public String conditionalOnMissingBeanUserTest() {
        System.out.println(conditionalOnMissingBeanUser);
        return "Sucess";
    }


    @Autowired
    private EnableConfigurationPropertiesTest enableConfigurationPropertiesTest;


    @RequestMapping("enableConfigurationPropertiesTest")
    public String enableConfigurationPropertiesTest() {
        System.out.println(JSON.toJSONString(enableConfigurationPropertiesTest));
        return "Sucess";
    }

}
