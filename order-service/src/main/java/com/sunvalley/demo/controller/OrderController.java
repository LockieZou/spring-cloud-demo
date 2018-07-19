package com.sunvalley.demo.controller;

import com.sunvalley.demo.enums.ApiMsgEnum;
import com.sunvalley.demo.model.ShopOrder;
import com.sunvalley.demo.service.OrderService;
import com.sunvalley.demo.vo.BaseReturnVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 类或方法的功能描述 :TODO
 *
 * @author: logan.zou
 * @date: 2018-06-21 10:20
 */
@Slf4j
@RestController
@RequestMapping("/order")
public class OrderController {
    @Value("${server.port}")
    private String port;

    @Autowired
    OrderService orderService;

    /**
     * 获取服务端口号
     * @return
     */
    @GetMapping("/getPort")
    public String getPort() {
        log.info("order-service port：" + port);
        return "order-service port：" + port;
    }

    /**
     * 根据订单ID查询
     * @param id
     * @return
     */
    @GetMapping("/getOrderById/{id}")
    public BaseReturnVO getOrderById(@PathVariable("id") Integer id) {
        if (null == id) {
            BaseReturnVO baseReturnVO = new BaseReturnVO();
            baseReturnVO.setResCode(ApiMsgEnum.COMMON_SERVER_ERROR.getResCode());
            baseReturnVO.setResDes("order id is empty!");
            return baseReturnVO;
        }
        try {
            ShopOrder shopOrder = orderService.getOrderById(id);
            return new BaseReturnVO(shopOrder);
        } catch (Exception e) {
            log.error("getAddressByOrderId error!" + e.getMessage());
            BaseReturnVO baseReturnVO = new BaseReturnVO();
            baseReturnVO.setResCode(ApiMsgEnum.INTERNAL_SERVER_ERROR.getResCode());
            baseReturnVO.setResDes(ApiMsgEnum.INTERNAL_SERVER_ERROR.getResDes());
            return baseReturnVO;
        }
    }
} 

