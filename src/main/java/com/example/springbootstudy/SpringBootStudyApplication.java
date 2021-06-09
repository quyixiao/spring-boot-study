package com.example.springbootstudy;

import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
public class SpringBootStudyApplication {

	public static void main(String[] args) {
		SpringApplication springApplication = new SpringApplication(SpringBootStudyApplication.class);
		springApplication.setBannerMode(Banner.Mode.CONSOLE);
		springApplication.run(args);
		//SpringApplication.run(SpringBootStudyApplication.class, args);
	}

}
