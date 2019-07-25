package com.lockie.user.remote;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * 类或方法的功能描述 : 调用订单服务
 *
 * @author: logan.zou
 * @date: 2018-06-21 10:27
 */
@FeignClient(value = "order-service", fallback = OrderRemoteHystrix.class)
public interface OrderRemote {
    @GetMapping("/order/getPort")
    String getPort();
}
