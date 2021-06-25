package com.example.springbootstudy.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.AcknowledgeMode;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author wutao
 * @description mq配置
 * @date 2020-12-25
 */
@Configuration
@Slf4j
public class RabbitConfig {

    @Autowired
    private CachingConnectionFactory connectionFactory;

    @Bean
    public Queue rabbitTestQueue(@Value("${eb.config.rabbitQueue.aaaa}") String queueName) {
        return new Queue(queueName);
    }

    @Bean(name = "simpleRabbitListenerContainerFactory")
    public SimpleRabbitListenerContainerFactory simpleRabbitListenerContainerFactory() {
        SimpleRabbitListenerContainerFactory listenerContainerFactory = new SimpleRabbitListenerContainerFactory();
        listenerContainerFactory.setConnectionFactory(connectionFactory);
        listenerContainerFactory.setConcurrentConsumers(5);
        listenerContainerFactory.setMaxConcurrentConsumers(5);
        listenerContainerFactory.setPrefetchCount(5);//预处理消息个数
        listenerContainerFactory.setAcknowledgeMode(AcknowledgeMode.MANUAL);//开启消息确认机制
        return listenerContainerFactory;
    }

}
