package com.lockie.eureka;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@EnableEurekaServer
@SpringBootApplication
public class EurekaServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(EurekaServiceApplication.class, args);
		Logger logger = LoggerFactory.getLogger(EurekaServiceApplication.class);
		logger.info("*****************");
		logger.info("**** 启动成功 ****");
		logger.info("*****************");
	}
}
