package com.example.springbootstudy.listener;

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

//@Component
@Slf4j
public class RabbitBussListenerbbbb {


    //@RabbitHandler
  //  @RabbitListener(queues = "#{rabbitTestQueuebbbb.name}")
    public void consumeMessage(@Payload String message, @Header(AmqpHeaders.DELIVERY_TAG) long delivertTag, Channel channel) {
        try {
            System.out.println("-------接收到消息：" + message);
           // Thread.sleep(3000);

            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String bs[] = message.split(" ");
            System.out.println("消费掉消息：" +   message  +       "  发送时间："+df.format(new Date(Long.parseLong(bs[bs.length-1]))) + "     消费完成时间："+ df.format(new Date()));
        } catch (Exception e) {
            log.error("处理异常", e);
        } finally {
            try {
                    channel.basicAck(delivertTag,true);
            } catch (Exception e) {
                log.error("确实消息失败",e);
            }
        }
    }

}