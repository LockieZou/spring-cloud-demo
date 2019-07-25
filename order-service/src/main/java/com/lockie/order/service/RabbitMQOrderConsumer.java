package com.lockie.order.service;

import com.alibaba.fastjson.JSON;
import com.lockie.order.model.ShopOrder;
import com.lockie.order.mapper.ShopOrderMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Date;
import java.util.Objects;

/**
 * 类或方法的功能描述 : 订单消息消费者-模拟订单延迟队列，未支付订单修改为已取消
 *
 * @author: logan.zou
 * @date: 2018-08-03 13:48
 */
@Slf4j
@Component
public class RabbitMQOrderConsumer {
    @Autowired
    ShopOrderMapper shopOrderMapper;

    @RabbitListener(queues = "user.delay.queue", containerFactory = "singleListenerContainer")
    public void consumeMessage(@RequestBody ShopOrder shopOrder) {
        try {
            log.info("消息者监听消息：{}", JSON.toJSON(shopOrder));
            if (Objects.equals(10, shopOrder.getOrderStatus())) {
                shopOrder.setOrderStatus(60);
                shopOrder.setUpdateDate(new Date());
                shopOrderMapper.updateByPrimaryKeySelective(shopOrder);
            }
        } catch (Exception e) {
            log.error("消息解析异常",e.fillInStackTrace());
        }
    }
} 

