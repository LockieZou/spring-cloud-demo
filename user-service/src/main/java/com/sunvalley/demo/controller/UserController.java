package com.sunvalley.demo.controller;

import com.sunvalley.demo.remote.OrderRemote;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 类或方法的功能描述 :TODO
 *
 * @author: logan.zou
 * @date: 2018-06-21 11:46
 */
@Slf4j
@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    OrderRemote orderRemote;
    @Value("${server.port}")
    String port;

    /**
     * 获取用户服务的端口
     * @return
     */
    @GetMapping("/getPort")
    public String getPort() {
        log.info("user-service port：" + port);
        return "user-service port：" + port;
    }

    /**
     * 获取订单服务的端口
     * @return
     */
    @GetMapping("/getOrderPort")
    public String getOrderPort() {
        log.info("user-order-service port：" + orderRemote.getPort());
        return "user-order-service port：" + orderRemote.getPort();
    }
} 

