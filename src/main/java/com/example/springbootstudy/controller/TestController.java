package com.example.springbootstudy.controller;

import com.alibaba.fastjson.JSON;
import com.example.springbootstudy.entity.TestUser;
import com.example.springbootstudy.mapper.TestUserMapper;
import com.example.springbootstudy.service.*;
import com.example.springbootstudy.service.impl.Auto;
import com.example.springbootstudy.service.impl.Intelligent;
import com.example.springbootstudy.utils.SpringContextUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.aop.scope.ScopedObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.core.env.Environment;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class TestController {

    @Autowired
    private HelloService helloService;

    /*@Value("${test1.profile}")
    private String profile1;*/
    @Value("${test.profile}")
    private String profile;


    // @Value("${bean.message}")
    private String beanMessage;


    @Autowired
    private TestUserMapper testUserMapper;

    @Autowired
    private RedisTemplate redisTemplate;


/*    @Autowired
    private ConditionalOnClassUser conditionalOnClassUser;*/

    @RequestMapping("/home")
    public String home() {
        System.out.println(beanMessage);
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
            System.out.println("??????????????????pageNum,pageSize ????????????????????????????????????");
        }
        System.out.println("====pageNum==" + pageNum + ", pageSize = " + pageSize);
        return "SUCESS";

    }

    @RequestMapping("select")
    public String select() {
        helloService.sayHello();
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


    @RequestMapping("importResourceTestBeanTest")
    public String importResourceTestBeanTest() {
        ImportResourceTestBean importResourceTestBean = SpringContextUtils.getBean(ImportResourceTestBean.class);
        System.out.println(importResourceTestBean);
        return "Sucess";
    }

    @RequestMapping("processInterfaceCTest")
    public String processInterfaceCTest() {
        ProcessInterfaceB processInterfaceB = SpringContextUtils.getBean(ProcessInterfaceB.class);
        ProcessInterfaceC processInterfaceC = SpringContextUtils.getBean(ProcessInterfaceC.class);
        System.out.println(processInterfaceB);
        System.out.println(processInterfaceC);
        return "Sucess";
    }

    @RequestMapping("superClassConfigTest")
    public String superClassConfigTest() {
        SuperClassConfigA superClassConfigA = SpringContextUtils.getBean(SuperClassConfigA.class);
        System.out.println(superClassConfigA);
        return "Sucess";
    }

    @RequestMapping("importATest")
    public String importATest() {
        ImportA importA = SpringContextUtils.getBean(ImportA.class);
        System.out.println(importA);
        return "Sucess";
    }

/*
    @Autowired
    private ScopedProxyModeA scopedProxyModeA;

    @Autowired
    private IScopedProxyModeA iScopedProxyModeA;
*/

    @RequestMapping("scopedProxyModeTest")
    public String scopedProxyModeTest() {
        ScopedProxyModeA importA = (ScopedProxyModeA) SpringContextUtils.getBean("scopedTarget.scopedProxyModeA");
        System.out.println(importA);
        Object object = SpringContextUtils.getBean("scopedProxyModeA");
        System.out.println(object);

/*

        System.out.println(iScopedProxyModeA);


*/


        importA.aaaaaa();
        return "Sucess";
    }


    @RequestMapping("scopedProxyModeBTest")
    public String scopedProxyModeBTest() {
        ScopedObject importA = (ScopedObject) SpringContextUtils.getBean("scopedObject");
        Object iScopedProxyModexxx = importA.getTargetObject();
        System.out.println(iScopedProxyModexxx);
        return "Sucess";
    }


    @RequestMapping("myCarTest")
    public String myCarTest() {
        Auto car = (Auto) SpringContextUtils.getBean("myCar");


        car.driving();
        Intelligent intelligentCar = (Intelligent) car;
        intelligentCar.selfDriving();

        return "Sucess";
    }


    @RequestMapping("redistTest")
    public String redistTest() {

        return "Sucess";
    }


    @Value("${eb.config.rabbitQueue.aaaa}")
    public String routingKey1;



    @Value("${eb.config.rabbitQueue.bbbb}")
    public String routingKey2;


    @Autowired
    private RabbitTemplate rabbitTemplate;

    @RequestMapping("rabbitTest")
    public String rabbitTest() {
        String message = "?????? " + System.currentTimeMillis();
        rabbitTemplate.convertAndSend(routingKey1, message);
        System.out.println("??????????????????" + message);
        return "Sucess";
    }


    @RequestMapping("rabbitTest2")
    public String rabbitTest2internalRabbitListenerAnnotationProcessor() {
        for (int i = 0; i < 100; i++) {
            String message = "?????? " + i + " " + System.currentTimeMillis();
            rabbitTemplate.convertAndSend(routingKey1, message);
            log.info(" ??????????????????" + message);
        }
        return "Sucess";
    }

    @RequestMapping("rabbitTestbbbb")
    public String rabbitTestbbbb() {
        String message = "?????? " + System.currentTimeMillis();
        rabbitTemplate.convertAndSend(routingKey2, message);
        System.out.println("??????????????????" + message);
        return "Sucess";
    }



    @Value("${eb.config.rabbitQueue.retry}")
    public String retryQueueName;

    @RequestMapping("retryQueueNameTest")
    public String retryQueueName() {
        String message = "?????? " + System.currentTimeMillis();
        rabbitTemplate.convertAndSend(retryQueueName, message);
        System.out.println("??????????????????" + message);
        return "Sucess";
    }


    @Value("${eb.config.rabbitQueue.complexRetry}")
    public String complexRetry;

    @RequestMapping("complexRetryTest")
    public String complexRetry() {
        String message = "?????? " + System.currentTimeMillis();
        rabbitTemplate.convertAndSend(complexRetry, message);
        System.out.println("??????????????????" + message);
        return "Sucess";
    }



    @Value("${eb.config.rabbitQueue.channelTxQueueName}")
    public String channelTxQueueName;

    @RequestMapping("channelTxQueueNameTest")
    public String channelTxQueueNameTest() {
        String message = "?????? " + System.currentTimeMillis();
        rabbitTemplate.convertAndSend(channelTxQueueName, message);
        System.out.println("??????????????????" + message);
        return "Sucess";
    }



    @Value("${eb.config.rabbitQueue.transactionManagerQueueName}")
    public String transactionManagerQueueName;

    @RequestMapping("transactionManagerQueueNameTest")
    public String transactionManagerQueueNameTest() {
        String message = "?????? " + System.currentTimeMillis();
        rabbitTemplate.convertAndSend(transactionManagerQueueName, message);
        System.out.println("??????????????????" + message);
        return "Sucess";
    }

    @Value("${eb.config.rabbitQueue.messageConvertQueueName}")
    public String messageConvertQueueName;

    @RequestMapping("messageConvertQueueNameTest")
    public String messageConvertQueueNameTest() {
        TestUser testUser = new TestUser();
        testUser.setRealName("??????");
        rabbitTemplate.convertAndSend(messageConvertQueueName, testUser);
        System.out.println("??????????????????" + JSON.toJSONString(testUser));
        return "Sucess";
    }

    @Value("${eb.config.rabbitQueue.customArgumentName}")
    public String customArgumentName;

    @RequestMapping("customArgumentNameTest")
    public String customArgumentNameTest() {
        String message = "??????xxx " + System.currentTimeMillis();
        rabbitTemplate.convertAndSend(customArgumentName, message);
        System.out.println("??????????????????" + message);
        return "Sucess";
    }

    @Value("${eb.config.rabbitQueue.autoCreateQueue}")
    public String autoCreateQueue;

    @RequestMapping("autoCreateQueueTest")
    public String autoCreateQueueTest() {
        String message = "??????xxx " + System.currentTimeMillis();
        rabbitTemplate.convertAndSend(autoCreateQueue, message);
        System.out.println("??????????????????" + message);
        return "Sucess";
    }
}



