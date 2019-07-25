package com.lockie.common;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CommonServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(CommonServiceApplication.class, args);
		Logger logger = LoggerFactory.getLogger(CommonServiceApplication.class);
		logger.info("*****************");
		logger.info("**** 启动成功 ****");
		logger.info("*****************");
	}
}
