package com.lockie.quartz;

import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan("com.lockie.quartz.mapper")
@SpringBootApplication()
public class QuartzServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(QuartzServiceApplication.class, args);
        Logger logger = LoggerFactory.getLogger(QuartzServiceApplication.class);
        logger.info("*****************");
        logger.info("**** 启动成功 ****");
        logger.info("*****************");
    }

}
