package com.sunvalley.demo.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 类或方法的功能描述 :TODO
 *
 * @author: logan.zou
 * @date: 2018-06-22 16:18
 */
@Slf4j
@RestController
@RequestMapping("/product")
public class ProductController {
    @Value("${server.port}")
    private String port;
    /**
     * 获取服务端口号
     * @return
     */
    @GetMapping("/getPort")
    public String getPort() {
        log.info("product-service port：" + port);
        return "product-service port：" + port;
    }
} 

