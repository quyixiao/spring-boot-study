package com.example.springbootstudy.listener;

import com.alibaba.fastjson.JSON;
import com.example.springbootstudy.entity.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class RabbitCustomArgumentListener {





    @RabbitHandler
    @RabbitListener(queues = "#{customArgumentName.name}")
    public void consumeMessage(User user) {
        System.out.println(JSON.toJSONString(user));
    }

}