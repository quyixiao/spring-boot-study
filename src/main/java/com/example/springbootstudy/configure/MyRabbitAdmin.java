package com.example.springbootstudy.configure;

import com.alibaba.fastjson.JSON;
import com.example.springbootstudy.process.Processor;
import com.example.springbootstudy.utils.SpringContextUtils;
import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import org.springframework.amqp.core.Declarable;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.ChannelProxy;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.ChannelCallback;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.listener.MessageListenerContainer;
import org.springframework.amqp.rabbit.listener.RabbitListenerEndpointRegistry;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.util.CollectionUtils;

import java.io.IOException;
import java.util.*;
import java.util.concurrent.TimeoutException;

public class MyRabbitAdmin extends RabbitAdmin   implements BeanFactoryAware {

    private ApplicationContext applicationContext;

    private BeanFactory beanFactory;

    private boolean declareCollections = true;

    public MyRabbitAdmin(ConnectionFactory connectionFactory, ApplicationContext applicationContext) {
        super(connectionFactory);
        this.applicationContext = applicationContext;
    }

    @Override
    public void initialize() {
        super.initialize();
        RabbitListenerEndpointRegistry registry = SpringContextUtils.getBean(RabbitListenerEndpointRegistry.class);
        Collection<MessageListenerContainer> messageListenerContainers = registry.getListenerContainers();
        Collection<Queue> contextQueues = new LinkedList<Queue>(
                this.applicationContext.getBeansOfType(Queue.class).values());
        Collection<Collection> collections = this.declareCollections
                ? this.applicationContext.getBeansOfType(Collection.class, false, false).values()
                : Collections.<Collection>emptyList();
        for (Collection<?> collection : collections) {
            if (collection.size() > 0 && collection.iterator().next() instanceof Declarable) {
                for (Object declarable : collection) {
                    if (declarable instanceof Queue) {
                        contextQueues.add((Queue) declarable);
                    }
                }
            }
        }
        final Collection<Queue> exitQueues = filterDeclarables(contextQueues);
        List<String> exitsQueueNames = new ArrayList<>();
        final Collection<Queue> queues = new ArrayList<>();
        for (Queue exitQueue : exitQueues) {
            exitsQueueNames.add(exitQueue.getName());
            System.out.println("exit queueName " + exitQueue.getName());
        }

        for (MessageListenerContainer messageListenerContainer : messageListenerContainers) {
            if (messageListenerContainer instanceof SimpleMessageListenerContainer) {
                String[] queueNames = ((SimpleMessageListenerContainer) messageListenerContainer).getQueueNames();
                for (String queueName : queueNames) {
                    System.out.println("=======queueName====" + queueName);
                }
            }
        }
        System.out.println("========MyRabbitAdmin 被 ===========");
        ResourceLoader resourceLoader = new DefaultResourceLoader();
        Resource resource = resourceLoader.getResource("application-" + SpringContextUtils.getActiveProfile() + ".yml");
        Processor processor = new Processor(resource, null);
        Map<String,String> notExitQueue = new HashMap<>();
        Map<String, Object> source = processor.process();
        // 获取容器中所有没有声明的队列
        for(Map.Entry<String,Object> map : source.entrySet()){
            if(map.getKey().startsWith("eb.config.rabbitQueue")){
                System.out.println("key : " + map.getKey() + " value : " + map.getValue());
                if(!exitsQueueNames.contains(map.getValue())){
                    String queueName = map.getKey().replaceAll("eb.config.rabbitQueue.","");
                    System.out.println("向容器中注册队列： " + queueName);
                    notExitQueue.put(queueName,map.getValue().toString());
                }
            }
        }
        System.out.println("=======notExitQueue=====" + JSON.toJSONString(notExitQueue));
        if(!notExitQueue.isEmpty()){
           for(Map.Entry<String,String> notExitQue:notExitQueue.entrySet() ){
               Queue queue = new Queue(notExitQue.getValue());
               queues.add(queue);
               if(beanFactory instanceof DefaultListableBeanFactory){
                   ((DefaultListableBeanFactory) beanFactory).registerSingleton(notExitQue.getKey(),queue);
               }
           }
        }
        if (!CollectionUtils.isEmpty(queues)) {
            getRabbitTemplate().execute(new ChannelCallback<Object>() {
                @Override
                public Object doInRabbit(Channel channel) throws Exception {
                    //声明队列
                    declareQueues(channel, queues.toArray(new Queue[queues.size()]));
                    return null;
                }
            });
        }
    }
    private <T extends Declarable> Collection<T> filterDeclarables(Collection<T> declarables) {
        Collection<T> filtered = new ArrayList<T>();
        for (T declarable : declarables) {
            Collection<?> adminsWithWhichToDeclare = declarable.getDeclaringAdmins();
            if (declarable.shouldDeclare() &&
                    (adminsWithWhichToDeclare.isEmpty() || adminsWithWhichToDeclare.contains(this))) {
                filtered.add(declarable);
            }
        }
        return filtered;
    }

    private AMQP.Queue.DeclareOk[] declareQueues(final Channel channel, final Queue... queues) throws IOException {
        List<AMQP.Queue.DeclareOk> declareOks = new ArrayList<AMQP.Queue.DeclareOk>(queues.length);
        for (int i = 0; i < queues.length; i++) {
            Queue queue = queues[i];
            if (!queue.getName().startsWith("amq.")) {
                if (this.logger.isDebugEnabled()) {
                    this.logger.debug("declaring Queue '" + queue.getName() + "'");
                }
                try {
                    try {
                        AMQP.Queue.DeclareOk declareOk = channel.queueDeclare(queue.getName(), queue.isDurable(),
                                queue.isExclusive(), queue.isAutoDelete(), queue.getArguments());
                        declareOks.add(declareOk);
                    } catch (IllegalArgumentException e) {
                        if (this.logger.isDebugEnabled()) {
                            this.logger.error("Exception while declaring queue: '" + queue.getName() + "'");
                        }
                        try {
                            if (channel instanceof ChannelProxy) {
                                ((ChannelProxy) channel).getTargetChannel().close();
                            }
                        } catch (TimeoutException e1) {
                        }
                        throw new IOException(e);
                    }
                } catch (IOException e) {
                    logger.error("队列失败", e);
                }
            } else if (this.logger.isDebugEnabled()) {
                this.logger.debug(queue.getName() + ": Queue with name that starts with 'amq.' cannot be declared.");
            }
        }
        return declareOks.toArray(new AMQP.Queue.DeclareOk[declareOks.size()]);
    }

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        this.beanFactory = beanFactory;
    }
}
