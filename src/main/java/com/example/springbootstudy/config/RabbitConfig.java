package com.example.springbootstudy.config;

import com.example.springbootstudy.listener.MyRetryOperationsInterceptor;
import com.example.springbootstudy.listener.MySimpleRetryPolicy;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.AcknowledgeMode;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.config.RetryInterceptorBuilder;
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.retry.MessageRecoverer;
import org.springframework.amqp.rabbit.retry.RejectAndDontRequeueRecoverer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.amqp.RabbitProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.retry.RetryOperations;
import org.springframework.retry.RetryPolicy;
import org.springframework.retry.backoff.ExponentialBackOffPolicy;
import org.springframework.retry.interceptor.MethodInvocationRecoverer;
import org.springframework.retry.interceptor.RetryOperationsInterceptor;
import org.springframework.retry.policy.SimpleRetryPolicy;
import org.springframework.retry.support.RetryTemplate;

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


    @Bean
    public Queue rabbitTestQueuebbbb(@Value("${eb.config.rabbitQueue.bbbb}") String queueName) {
        return new Queue(queueName);

    }

    @Bean
    public Queue rabbitTestRestryQueue(@Value("${eb.config.rabbitQueue.retry}") String queueName) {
        return new Queue(queueName);
    }



    @Bean
    public Queue rabbitTestComplexRestryQueue(@Value("${eb.config.rabbitQueue.complexRetry}") String queueName) {
        return new Queue(queueName);
    }


    @Bean(name = "rongshuEnterPieceSimpleRabbitListenerContainerFactory")
    public SimpleRabbitListenerContainerFactory rongshuEnterPieceSimpleRabbitListenerContainerFactory() {
        SimpleRabbitListenerContainerFactory listenerContainerFactory = new SimpleRabbitListenerContainerFactory();
        listenerContainerFactory.setConnectionFactory(connectionFactory);
        listenerContainerFactory.setConcurrentConsumers(5);
        listenerContainerFactory.setMaxConcurrentConsumers(10);
        listenerContainerFactory.setPrefetchCount(5);//预处理消息个数
        listenerContainerFactory.setAcknowledgeMode(AcknowledgeMode.MANUAL);//开启消息确认机制
        return listenerContainerFactory;
    }


    @Bean(name = "simpleRabbitListenerContainerFactorybbbb")
    public SimpleRabbitListenerContainerFactory simpleRabbitListenerContainerFactorybbbb() {
        SimpleRabbitListenerContainerFactory listenerContainerFactory = new SimpleRabbitListenerContainerFactory();
        listenerContainerFactory.setConnectionFactory(connectionFactory);
        listenerContainerFactory.setConcurrentConsumers(5);
        listenerContainerFactory.setMaxConcurrentConsumers(10);
        listenerContainerFactory.setPrefetchCount(5);//预处理消息个数
        listenerContainerFactory.setAcknowledgeMode(AcknowledgeMode.MANUAL);//开启消息确认机制
        return listenerContainerFactory;
    }


    @Bean(name = "simpleRetryRabbitListenerContainerFactory")
    public SimpleRabbitListenerContainerFactory simpleRetryRabbitListenerContainerFactory() {
        SimpleRabbitListenerContainerFactory listenerContainerFactory = new SimpleRabbitListenerContainerFactory();
        listenerContainerFactory.setConnectionFactory(connectionFactory);
        listenerContainerFactory.setConcurrentConsumers(1);
        listenerContainerFactory.setMaxConcurrentConsumers(1);
        listenerContainerFactory.setPrefetchCount(1);//预处理消息个数
        listenerContainerFactory.setAcknowledgeMode(AcknowledgeMode.AUTO);//开启消息确认机制

        RabbitProperties.ListenerRetry retryConfig = new RabbitProperties.ListenerRetry();
        retryConfig.setEnabled(true);
        retryConfig.setMaxAttempts(3);
        retryConfig.setInitialInterval(3000);
        retryConfig.setMaxInterval(5000);


        if (retryConfig.isEnabled()) {
            RetryInterceptorBuilder<?> builder = (retryConfig.isStateless()
                    ? RetryInterceptorBuilder.stateless()
                    : RetryInterceptorBuilder.stateful());
            builder.maxAttempts(retryConfig.getMaxAttempts());
            builder.backOffOptions(retryConfig.getInitialInterval(),
                    retryConfig.getMultiplier(), retryConfig.getMaxInterval());
            MessageRecoverer recoverer = (new RejectAndDontRequeueRecoverer());
            builder.recoverer(recoverer);
            listenerContainerFactory.setAdviceChain(builder.build());
        }
        return listenerContainerFactory;
    }


    @Bean(name = "complexRetryRabbitListenerContainerFactory")
    public SimpleRabbitListenerContainerFactory complexRetryRabbitListenerContainerFactory() {
        SimpleRabbitListenerContainerFactory listenerContainerFactory = new SimpleRabbitListenerContainerFactory();
        listenerContainerFactory.setConnectionFactory(connectionFactory);
        listenerContainerFactory.setConcurrentConsumers(1);
        listenerContainerFactory.setMaxConcurrentConsumers(1);
        listenerContainerFactory.setPrefetchCount(1);//预处理消息个数
        listenerContainerFactory.setAcknowledgeMode(AcknowledgeMode.AUTO);//开启消息确认机制

        RabbitProperties.ListenerRetry retryConfig = new RabbitProperties.ListenerRetry();
        retryConfig.setEnabled(true);
        if (retryConfig.isEnabled()) {
            RetryTemplate retryTemplate = new RetryTemplate();
            RetryPolicy retryPolicy = new SimpleRetryPolicy(8);
            retryTemplate.setRetryPolicy(retryPolicy);
            ExponentialBackOffPolicy exponentialBackOffPolicy = new ExponentialBackOffPolicy();
            //初始等待时间
            exponentialBackOffPolicy.setInitialInterval(1000);
            //时间等待倍数
            exponentialBackOffPolicy.setMultiplier(2);
            //最大等待时间
            exponentialBackOffPolicy.setMaxInterval(5000);
            retryTemplate.setBackOffPolicy(exponentialBackOffPolicy);

            MyRetryOperationsInterceptor retryInterceptor = new MyRetryOperationsInterceptor();

            retryInterceptor.setRetryOperations(retryTemplate);
            final MessageRecoverer messageRecoverer = new RejectAndDontRequeueRecoverer();
            retryInterceptor.setRecoverer(new MethodInvocationRecoverer<Void>() {
                public Void recover(Object[] args, Throwable cause) {
                    Message message = (Message) args[1];
                    if (messageRecoverer == null) {
                        log.info("messageRecoverer == null");
                    }
                    else {
                        messageRecoverer.recover(message, cause);
                    }
                    return null;
                }
            });
            listenerContainerFactory.setAdviceChain(retryInterceptor);
        }
        return listenerContainerFactory;
    }





    @Bean(name = "newComplexRetryRabbitListenerContainerFactory")
    public SimpleRabbitListenerContainerFactory newComplexRetryRabbitListenerContainerFactory() {
        SimpleRabbitListenerContainerFactory listenerContainerFactory = new SimpleRabbitListenerContainerFactory();
        listenerContainerFactory.setConnectionFactory(connectionFactory);
        listenerContainerFactory.setConcurrentConsumers(1);
        listenerContainerFactory.setMaxConcurrentConsumers(1);
        listenerContainerFactory.setPrefetchCount(1);//预处理消息个数
        listenerContainerFactory.setAcknowledgeMode(AcknowledgeMode.AUTO);//开启消息确认机制

        RabbitProperties.ListenerRetry retryConfig = new RabbitProperties.ListenerRetry();
        retryConfig.setEnabled(true);
        if (retryConfig.isEnabled()) {
            RetryTemplate retryTemplate = new RetryTemplate();
            MySimpleRetryPolicy retryPolicy = new MySimpleRetryPolicy();
            retryTemplate.setRetryPolicy(retryPolicy);
            ExponentialBackOffPolicy exponentialBackOffPolicy = new ExponentialBackOffPolicy();
            //初始等待时间
            exponentialBackOffPolicy.setInitialInterval(1000);
            //时间等待倍数
            exponentialBackOffPolicy.setMultiplier(2);
            //最大等待时间
            exponentialBackOffPolicy.setMaxInterval(5000);
            retryTemplate.setBackOffPolicy(exponentialBackOffPolicy);
            RetryOperationsInterceptor retryInterceptor = new RetryOperationsInterceptor();
            retryInterceptor.setRetryOperations(retryTemplate);
            final MessageRecoverer messageRecoverer = new RejectAndDontRequeueRecoverer();
            retryInterceptor.setRecoverer(new MethodInvocationRecoverer<Void>() {
                public Void recover(Object[] args, Throwable cause) {
                    Message message = (Message) args[1];
                    if (messageRecoverer == null) {
                        log.info("messageRecoverer == null");
                    }
                    else {
                        messageRecoverer.recover(message, cause);
                    }
                    return null;
                }
            });
            listenerContainerFactory.setAdviceChain(retryInterceptor);
        }
        return listenerContainerFactory;
    }


}
