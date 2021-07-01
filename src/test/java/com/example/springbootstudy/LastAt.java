package com.example.springbootstudy;

import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;

import java.util.concurrent.atomic.AtomicLong;

public class LastAt {


    private final static AtomicLong lastNoMessageAlert = new AtomicLong();

    private static volatile long lastReceive = System.currentTimeMillis();



    public static void main(String[] args) {
        long now = System.currentTimeMillis();
        System.out.println(now);
        long lastAlertAt = lastNoMessageAlert.get();
        System.out.println(lastAlertAt);
        boolean b = lastNoMessageAlert
                .compareAndSet(lastAlertAt, now);
        System.out.println(b);
        System.out.println(lastNoMessageAlert.get());
    }
}
