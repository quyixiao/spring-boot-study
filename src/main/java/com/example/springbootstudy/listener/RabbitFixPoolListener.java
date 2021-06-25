package com.example.springbootstudy.listener;

import com.rabbitmq.client.Channel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

//@Component
@Slf4j
public class RabbitFixPoolListener {


    private static ExecutorService pool = Executors.newFixedThreadPool(5);


    //@RabbitHandler
    //@RabbitListener(queues = "#{rabbitTestQueue.name}")
    public void consumeMessage(@Payload String message, @Header(AmqpHeaders.DELIVERY_TAG) long delivertTag, Channel channel) {
        try {
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String bs[] = message.split(" ");
            log.info(delivertTag + "-------接收到消息：" + message + "  发送时间：" + df.format(new Date(Long.parseLong(bs[bs.length - 1]))) + "     消费完成时间：" + df.format(new Date()));
            pool.submit(new MycallableA(channel, delivertTag, message));
            //Thread.sleep(3000);
            //SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            //String bs[] = message.split(" ");
            //System.out.println("消费掉消息：" +   message  +       "  发送时间："+df.format(new Date(Long.parseLong(bs[bs.length-1]))) + "     消费完成时间："+ df.format(new Date()));
            // channel.basicAck(delivertTag,true);
        } catch (Exception e) {
            log.error("处理异常", e);
        } finally {

        }
    }

    static class MycallableA implements Callable {
        private String message;
        private Channel channel;
        private long delivertTag;

        public MycallableA(Channel channel, long delivertTag, String message) {
            this.message = message;
            this.channel = channel;
            this.delivertTag = delivertTag;
        }

        @Override
        public Object call() throws Exception {
            Random random = new Random();
            Thread.sleep(5000);
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String bs[] = message.split(" ");
            log.info(delivertTag + " = 消费掉消息：" + message + "  发送时间：" + df.format(new Date(Long.parseLong(bs[bs.length - 1]))) + "     消费完成时间：" + df.format(new Date()));
            channel.basicAck(delivertTag, true);
            return null;
        }
    }

    public static void main(String[] args) {
        String a = "消费掉消息：测试 19 1624539160203";
        String bs[] = a.split(" ");
        System.out.println(bs[bs.length - 1]);
    }
}