package com.lockie.order.service;

import com.alibaba.druid.util.StringUtils;
import com.lockie.order.model.ShopOrder;
import com.lockie.order.model.ShopOrderExample;
import com.lockie.order.modelEX.ShopOrderEX;
import com.lockie.order.mapper.ShopOrderItemMapper;
import com.lockie.order.mapper.ShopOrderMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.AmqpException;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessagePostProcessor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.AbstractJavaTypeMapper;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * 类或方法的功能描述 : 订单服务
 *
 * @author: logan.zou
 * @date: 2018-07-13 11:17
 */
@Slf4j
@Service
public class OrderService {

    @Autowired
    ShopOrderMapper shopOrderMapper;
    @Autowired
    ShopOrderItemMapper shopOrderItemMapper;
    @Autowired
    RabbitTemplate rabbitTemplate;

    private static final String DELAY_EXCHANGE = "user.delay.exchange";

    /**
     * 根据订单ID查询
     * @param id
     * @return
     */
    public ShopOrder getOrderById(Integer id) {
        return shopOrderMapper.selectByPrimaryKey(id);
    }

    /**
     * 根据订单号查询
     * @param orderNo
     * @return
     */
    public ShopOrder getOrderByOrderNo(String orderNo) {
        if (StringUtils.isEmpty(orderNo)) {
            return null;
        }
        ShopOrderExample shopOrderExample = new ShopOrderExample();
        shopOrderExample.createCriteria().andOrderNoEqualTo(orderNo);
        List<ShopOrder> list = shopOrderMapper.selectByExample(shopOrderExample);
        if (null != list && list.size() > 0) {
            return list.get(0);
        } else {
            return null;
        }
    }

    /**
     * 保存订单信息
     * @param shopOrderEX
     * @return
     */
    public ShopOrderEX saveShopOrder(ShopOrderEX shopOrderEX) {
        if (null == shopOrderEX) {
            return null;
        }
        ShopOrderEX returnData = new ShopOrderEX();
        /**
         * 新增
         */
        if (null == shopOrderEX.getId()) {
            // 保存订单主表信息
            ShopOrder shopOrder = shopOrderEX;
            shopOrder.setCreateDate(new Date());
            shopOrder.setUpdateDate(new Date());
            shopOrderMapper.insert(shopOrder);

            // 保存订单明细信息
            if (null != shopOrderEX.getShopOrderItemList() && shopOrderEX.getShopOrderItemList().size() > 0) {
                shopOrderEX.getShopOrderItemList().forEach(shopOrderItem -> {
                    shopOrderItem.setCreateDate(new Date());
                    shopOrderItem.setUpdateDate(new Date());
                });
                shopOrderItemMapper.insertBatch(shopOrderEX.getShopOrderItemList());
            }

            // 返回数据
            returnData.setShopOrderItemList(shopOrderEX.getShopOrderItemList());
            BeanUtils.copyProperties(shopOrder, returnData);
        } else {
            /**
             * 更新
             */
            // 根据订单ID查询
            ShopOrder shopOrder = shopOrderMapper.selectByPrimaryKey(shopOrderEX.getId());
            if (null == shopOrder) {
                log.error("订单不存在");
            }

            // 更新订单主表信息
            shopOrder.setUpdateDate(new Date());
            shopOrderMapper.updateByPrimaryKey(shopOrder);

            // 更新明细信息
            if (null != shopOrderEX.getShopOrderItemList() && shopOrderEX.getShopOrderItemList().size() > 0) {
                shopOrderEX.getShopOrderItemList().forEach(shopOrderItem -> {
                    shopOrderItem.setUpdateDate(new Date());
                    shopOrderItemMapper.updateByPrimaryKey(shopOrderItem);
                });
            }

            // 返回数据
            returnData.setShopOrderItemList(shopOrderEX.getShopOrderItemList());
            BeanUtils.copyProperties(shopOrder, returnData);
        }

        return returnData;
    }

    /**
     * 订单未支付mq消息发送
     * @param shopOrder
     */
    public void sendUnPayOrderMessage(ShopOrder shopOrder) {
        if (null == shopOrder) {
            return;
        }
        /** 消息队列发送订单消息 **/

        final Long ttl = 10000L;
        rabbitTemplate.setMessageConverter(new Jackson2JsonMessageConverter());
        rabbitTemplate.setExchange(DELAY_EXCHANGE);
        rabbitTemplate.setRoutingKey("");
        rabbitTemplate.convertAndSend(shopOrder, new MessagePostProcessor() {
            @Override
            public Message postProcessMessage(Message message) throws AmqpException {
                message.getMessageProperties().setHeader(AbstractJavaTypeMapper.DEFAULT_CONTENT_CLASSID_FIELD_NAME, ShopOrderEX.class.getName());
                message.getMessageProperties().setExpiration(ttl + "");
                return message;

            }
        });
    }
} 

