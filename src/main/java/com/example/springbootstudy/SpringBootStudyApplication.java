package com.example.springbootstudy;

import com.example.springbootstudy.annotation.MyMapper;
import com.example.springbootstudy.mapper.BaseMapper;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.HashMap;
import java.util.Map;

@SpringBootApplication
@MapperScan(value = "com.example.springbootstudy",annotationClass = MyMapper.class)
public class SpringBootStudyApplication {

    public static void main(String[] args) {
        SpringApplication springApplication = new SpringApplication(SpringBootStudyApplication.class);
        Map<String, Object> defaultProperties = new HashMap<>();


        defaultProperties.put("pageNum", 1);
        defaultProperties.put("pageSize", 20);
        springApplication.setDefaultProperties(defaultProperties);

        springApplication.setBannerMode(Banner.Mode.CONSOLE);
        springApplication.run(args);
        //SpringApplication.run(SpringBootStudyApplication.class, args);
    }

}
