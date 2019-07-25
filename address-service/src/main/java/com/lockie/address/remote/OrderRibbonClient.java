package com.lockie.address.remote;

import com.lockie.common.vo.BaseReturnVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * 类或方法的功能描述 : 使用ribbon调用 order-service，模拟负载均衡
 *
 * @author: logan.zou
 * @date: 2018-07-25 17:02
 */
@Service
public class OrderRibbonClient {
    @Autowired
    RestTemplate restTemplate;

    /**
     * 使用ribbon调用 order-service 的端口测试负载均衡
     * @return
     */
    public String getPort() {
        return restTemplate.getForObject("http://ORDER-SERVICE/order/getPort",String.class);
    }

    /**
     * 使用ribbon调用order-service的服务方法
     * @param id
     * @return
     */
    public BaseReturnVO getOrderById(Integer id) {
        BaseReturnVO baseReturnVO = restTemplate.getForObject("http://ORDER-SERVICE/order/getOrderById/"+id,BaseReturnVO.class);
        return baseReturnVO;
    }
} 

