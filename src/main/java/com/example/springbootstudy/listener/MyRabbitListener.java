package com.example.springbootstudy.listener;


import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;

//@Component
//@RabbitListeners({@RabbitListener(queues = "#{rabbitTestQueuebbbb.name}",containerFactory = "simpleRabbitListenerContainerFactory"),
//        @RabbitListener(queues = "#{rabbitTestQueuebbbb.name}",containerFactory = "simpleRabbitListenerContainerFactory")})
//@RabbitListener(queues = "#{rabbitTestQueuebbbb.name}",containerFactory = "simpleRabbitListenerContainerFactory")
public class MyRabbitListener {

    // @RabbitListeners({@RabbitListener(queues = "#{rabbitTestQueuebbbb.name}",containerFactory = "simpleRabbitListenerContainerFactory"),
//        @RabbitListener(queues = "#{rabbitTestQueuebbbb.name}",containerFactory = "simpleRabbitListenerContainerFactory")})
//    @RabbitListener(queues = "#{rabbitTestQueuebbbb.name}",containerFactory = "simpleRabbitListenerContainerFactory")
    //@RabbitListener(queues = "#{rabbitTestQueuebbbb.name}")
    //@RabbitListener(bindings = @QueueBinding(
//            value = @Queue(value = "HORSE_ANNOTATION_QUEUE", durable = "true"),
//            exchange = @Exchange(value = "HORSE_ANNOTATION_EXCHANGE", ignoreDeclarationExceptions = "true"),
//            key = "HORSE_ANNOTATION_KEY"))
    public void recive() { 

    }
}
