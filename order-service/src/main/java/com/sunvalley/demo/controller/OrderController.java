package com.sunvalley.demo.controller;

import com.sunvalley.demo.enums.ApiMsgEnum;
import com.sunvalley.demo.model.ShopOrder;
import com.sunvalley.demo.service.OrderService;
import com.sunvalley.demo.vo.BaseReturnVO;
import com.sunvalley.modelEX.ShopOrderEX;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

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

    /**
     * 订单保存
     * @param shopOrderEX
     * @return
     */
    @PostMapping("/saveShopOrder")
    public BaseReturnVO saveShopOrder(@RequestBody ShopOrderEX shopOrderEX) {
        if (null == shopOrderEX) {
            BaseReturnVO baseReturnVO = new BaseReturnVO();
            baseReturnVO.setResCode(ApiMsgEnum.COMMON_SERVER_ERROR.getResCode());
            baseReturnVO.setResDes("order is empty!");
            return baseReturnVO;
        }

        try {
            ShopOrderEX tmp = orderService.saveShopOrder(shopOrderEX);
            return new BaseReturnVO(tmp);
        } catch (Exception e) {
            log.error("saveShopOrder error!" + e.getMessage());
            BaseReturnVO baseReturnVO = new BaseReturnVO();
            baseReturnVO.setResCode(ApiMsgEnum.INTERNAL_SERVER_ERROR.getResCode());
            baseReturnVO.setResDes(ApiMsgEnum.INTERNAL_SERVER_ERROR.getResDes());
            return baseReturnVO;
        }
    }

    /**
     * 未支付的订单mq消息发送
     * @param shopOrder
     * @return
     */
    @PostMapping("/rabbitmq/sendUnPayOrderMessage")
    public BaseReturnVO sendUnPayOrderMessage(@RequestBody ShopOrder shopOrder) {
        if (null == shopOrder) {
            BaseReturnVO baseReturnVO = new BaseReturnVO();
            baseReturnVO.setResCode(ApiMsgEnum.COMMON_SERVER_ERROR.getResCode());
            baseReturnVO.setResDes("order is empty!");
            return baseReturnVO;
        }
        try {
            orderService.sendUnPayOrderMessage(shopOrder);
            return new BaseReturnVO(1);
        } catch (Exception e) {
            log.error("未支付订单消息发送异常！",e.fillInStackTrace());
            BaseReturnVO baseReturnVO = new BaseReturnVO();
            baseReturnVO.setResCode(ApiMsgEnum.INTERNAL_SERVER_ERROR.getResCode());
            baseReturnVO.setResDes(ApiMsgEnum.INTERNAL_SERVER_ERROR.getResDes());
            return baseReturnVO;
        }
    }
} 

