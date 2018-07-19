package com.sunvalley.demo.remote;

import org.springframework.stereotype.Component;

/**
 * 类或方法的功能描述 :TODO
 *
 * @author: logan.zou
 * @date: 2018-06-21 10:28
 */
@Component
public class OrderRemoteHystrix implements OrderRemote {
    @Override
    public String getPort() {
        return "order service 调用失败！";
    }
} 

