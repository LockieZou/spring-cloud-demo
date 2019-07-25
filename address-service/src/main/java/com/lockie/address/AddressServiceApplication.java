package com.lockie.address;

import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@EnableCircuitBreaker
@EnableEurekaClient
@EnableFeignClients
@MapperScan("com.lockie.address.mapper")
@EnableCaching		// 开启缓存
//@ServletComponentScan(basePackages = {"com.lockie.demo.config"})	// 扫描servlet和filter
@SpringBootApplication
public class AddressServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(AddressServiceApplication.class, args);
		Logger logger = LoggerFactory.getLogger(AddressServiceApplication.class);
		logger.info("*****************");
		logger.info("**** 启动成功 ****");
		logger.info("*****************");
	}

	/**
	 * 使用ribbon负载均衡调用其它服务
	 * @return
	 */
	@Bean
	@LoadBalanced
	RestTemplate restTemplate() {
		return new RestTemplate();
	}
}
