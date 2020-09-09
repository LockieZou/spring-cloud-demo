package com.lockie.order.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @author: lockie
 * @Date: 2020/7/3 11:29
 * @Description: redisson配置
 */
@Data
@ConfigurationProperties(prefix = "redisson")
//@ConditionalOnProperty("redisson.password")
//@ConditionalOnProperty(prefix = "redisson",name = "redisson.password",matchIfMissing = true)
public class RedissonProperties {

    private int timeOut = 3000;

//    @Value("${redisson.address}")
    private String address;

//    @Value("${redisson.password}")
    private String password;

    private int connectionPoolSize = 64;

    private int connectionMinimumIdleSize = 10;

    private int slaveConnectionPoolSize = 250;

    private int masterConnectionPoolSize = 250;

    private String[] sentineAddresses;

    private String masterName;
}
