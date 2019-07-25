package com.lockie.address.service;

import com.alibaba.fastjson.JSONObject;
import com.lockie.address.model.ShopAddress;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 类或方法的功能描述 :rabbitmq 发送消息者
 *
 * @author: logan.zou
 * @date: 2018-07-12 17:24
 */
@Service
public class RabbitMqSender {
    @Autowired
    AmqpTemplate amqpTemplate;

    /** ================= direct exchange 直接交换机消息接收 ==================== **/

    public void send(ShopAddress shopAddress) {
        System.out.println("AddressDirectSender: " + JSONObject.toJSON(shopAddress));
        amqpTemplate.convertAndSend("address.direct", shopAddress);
    }

    // ================= direct exchange 直接交换机消息接收 ==================== **/

    /** ================= topic exchange 主题交换机消息接收 ==================== **/

    // api.core 主题发送
    public void saveAddress(String msg) {
        System.out.println("api.core.address send message: " + msg);
        amqpTemplate.convertAndSend("coreExchange", "api.core.address", msg);
    }

    public void addressQuery(String msg) {
        System.out.println("api.core.address.query send message: " + msg);
        amqpTemplate.convertAndSend("coreExchange", "api.core.addre.query", msg);
    }

    // api.payment 主题发送
    public void order(String msg) {
        System.out.println("api.payment.order send message: " + msg);
        amqpTemplate.convertAndSend("paymentExchange", "api.payment.order", msg);
    }

    public void orderQuery(String msg) {
        System.out.println("api.payment.order.query send message：" + msg);
        amqpTemplate.convertAndSend("paymentExchange", "api.payment.order.query", msg);
    }

    public void orderDetailQuery(String msg) {
        System.out.println("api.payment.order.detail.query send message：" + msg);
        amqpTemplate.convertAndSend("paymentExchange", "api.payment.order.detail", msg);
    }

    // ================= topic exchange 主题交换机消息接收 ==================== **/


    /** ================= fanout exchange 扇形交换机消息接收 ==================== **/

    public void generateReport(String msg) {
        System.out.println("api.generate.report send message：" + msg);
        amqpTemplate.convertAndSend("reportExchange", "api.generate.report", msg);
    }

    // ================= fanout exchange 扇形交换机消息接收 ==================== **/


    /** ================= header exchange 头交换机消息接收 ==================== **/


    // ================= header exchange 头交换机消息接收 ==================== **/
} 

