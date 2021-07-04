package com.example.springbootstudy.listener;

import com.alibaba.fastjson.JSON;
import com.example.springbootstudy.entity.TestUser;
import com.rabbitmq.client.Channel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

@Component
@Slf4j
public class RabbitMessageConvertListener {


    @RabbitHandler
    @RabbitListener(queues = "#{messageConvertQueueName.name}", containerFactory = "messageConvertSimpleRabbitListenerContainerFactory")
    public void consumeMessage(String testUser, @Header(AmqpHeaders.DELIVERY_TAG) long delivertTag, Channel channel) {
        System.out.println(JSON.toJSONString(testUser));
    }

}