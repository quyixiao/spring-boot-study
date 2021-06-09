package com.example.springbootstudy;

import org.springframework.util.StopWatch;

public class TestStopWatch {

    public static void main(String[] args) throws Exception{
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();

        Thread.sleep(2300);

        stopWatch.stop();

        double time = stopWatch.getTotalTimeSeconds();
        System.out.println(time);


    }
}
