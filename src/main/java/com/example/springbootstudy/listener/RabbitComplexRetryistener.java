package com.example.springbootstudy.listener;

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
public class RabbitComplexRetryistener {


    @RabbitHandler
    @RabbitListener(queues = "#{rabbitTestComplexRestryQueue.name}",containerFactory = "newComplexRetryRabbitListenerContainerFactory")
    public void consumeMessage(@Payload String message, @Header(AmqpHeaders.DELIVERY_TAG) long delivertTag, Channel channel) {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        System.out.println("---complex----接收到消息：" + message + "，接收时间 ： " + df.format(new Date()));
/*
        int i = 0 ;
        int j = 0;
        int c = i /j;*/

        TestUser testUser = null;
        testUser.setGmtModified(new Date());

        String bs[] = message.split(" ");
        System.out.println("complex消费掉消息：" +   message  +       "  发送时间："+df.format(new Date(Long.parseLong(bs[bs.length-1]))) + "     消费完成时间："+ df.format(new Date()));

    }

}














