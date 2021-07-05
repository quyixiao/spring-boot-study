package com.example.springbootstudy.resolver;

import com.alibaba.fastjson.JSON;
import com.example.springbootstudy.process.Processor;
import com.example.springbootstudy.utils.SpringContextUtils;
import com.oracle.tools.packager.Log;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanExpressionContext;
import org.springframework.context.expression.StandardBeanExpressionResolver;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;

import java.util.HashMap;
import java.util.Map;

@Slf4j
public class MyStandardBeanExpressionResolver extends StandardBeanExpressionResolver {


    public String env;
    public MyStandardBeanExpressionResolver() {

    }

    public MyStandardBeanExpressionResolver(ClassLoader beanClassLoader,String env) {
        super(beanClassLoader);
        this.env = env;
    }

    @Override
    public Object evaluate(String value, BeanExpressionContext evalContext) throws BeansException {
        try {
            System.out.println("--------valuexxx--------------" + value);
            Object object = super.evaluate(value, evalContext);
            System.out.println("---" + object);
            return object;
        } catch (Exception e) {
            //        e.printStackTrace();
            log.info(" value = " + value + " 并没有申明队列，抛出异常，系统来帮忙申明队列  ");
        } catch (Throwable t) {
            //    t.printStackTrace();
            log.info(" value = " + value + " 并没有申明队列，系统来帮忙申明队列  ");
        }
        ResourceLoader resourceLoader = new DefaultResourceLoader();
        Resource resource = resourceLoader.getResource("application-"+env+".yml");
        Processor processor = new Processor(resource, null);
        Map<String, String> notExitQueue = new HashMap<>();
        Map<String, Object> source = processor.process();
        // 从配置文件中获取队列名
        for (Map.Entry<String, Object> map : source.entrySet()) {
            if (map.getKey().startsWith("eb.config.rabbitQueue")) {
                System.out.println("key : " + map.getKey() + " value : " + map.getValue());
                String queueName = map.getKey().replaceAll("eb.config.rabbitQueue.", "");
                notExitQueue.put(queueName, map.getValue().toString());
            }
        }
        System.out.println("=======notExitQueue=====" + JSON.toJSONString(notExitQueue));
        String a = value;
        a = a.substring(2);
        a = a.substring(0, a.length() - 1);
        String bs[] = a.split("\\.");
        String queueName = bs[0];
        String queue = notExitQueue.get(queueName);
        System.out.println(queue);
        return queue;
    }

    public static void main(String[] args) {
        String a = "#{autoCreateQueue.name}";
        a = a.substring(2);
        a = a.substring(0, a.length() - 1);
        String bs[] = a.split("\\.");
        System.out.println(bs[0]);
    }
}
