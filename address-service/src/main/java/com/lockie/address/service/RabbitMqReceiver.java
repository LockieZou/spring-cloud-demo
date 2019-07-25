package com.lockie.address.service;

import com.alibaba.fastjson.JSONObject;
import com.lockie.address.model.ShopAddress;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * 类或方法的功能描述 :rabbitmq 消息接收
 *
 * @author: logan.zou
 * @date: 2018-07-12 17:30
 */
@Component
public class RabbitMqReceiver {

    /** ================= direct exchange 直接交换机消息接收 ==================== **/

    @RabbitHandler
    @RabbitListener(queues = "address.direct")
    public void receive(ShopAddress shopAddress) {
        System.out.println("AddressDirectReceiver: " + JSONObject.toJSON(shopAddress));
    }

    // ================= direct exchange 直接交换机消息接收 ==================== **/


    /** ================= topic exchange 主题交换机消息接收 ==================== **/

    @RabbitHandler
    @RabbitListener(queues = "api.core")
    public void coreReceive(String msg) {
        System.out.println("api.core receive message: " + msg);
    }

    @RabbitHandler
    @RabbitListener(queues = "api.payment")
    public void paymentReceive(String msg) {
        System.out.println("api.payment receive message: " + msg);
    }

    // ================= topic exchange 主题交换机消息接收 ==================== **/



    /** ================= fanout exchange 扇形交换机消息接收 ==================== **/

    @RabbitHandler
    @RabbitListener(queues = "api.report.payment")
    public void payment(String msg) {
        System.out.println("api.report.payment receive message：" + msg);
    }

    @RabbitHandler
    @RabbitListener(queues = "api.report.refund")
    public void refund(String msg) {
        System.out.println("api.report.refund receive message：" + msg);
    }

    // ================= fanout exchange 扇形交换机消息接收 ==================== **/


    /** ================= header exchange 头交换机消息接收 ==================== **/


    // ================= header exchange 头交换机消息接收 ==================== **/
} 

