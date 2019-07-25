package com.lockie.address.config;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

/**
 * 类或方法的功能描述 :rabbitmq 配置
 *
 * @author: logan.zou
 * @date: 2018-07-12 16:12
 */
@Configuration
public class RabbitmqConfig {

    /** ===============direct Exchange直连交换机队列========== **/

    @Bean
    public Queue addressDirectQueue() {
        return new Queue("address.direct");
    }

    // ===============direct Exchange直连交换机队列========== **/


     /** ===============topic Exchange主题交换机队列========== **/

    @Bean
    public Queue coreQueue() {
        return new Queue("api.core");
    }


    @Bean
    public Queue paymentQueue() {
        return new Queue("api.payment");
    }


    @Bean
    public TopicExchange coreExchange() {
        return new TopicExchange("coreExchange");
    }

    @Bean
    public TopicExchange paymentExchange() {
        return new TopicExchange("paymentExchange");
    }

    @Bean
    public Binding bindCoreExchange(Queue coreQueue, TopicExchange coreExchange) {
        // 通配符 * 只能向后多匹配一层路径。
        return BindingBuilder.bind(coreQueue).to(coreExchange).with("api.core.*");
    }

    @Bean
    public Binding bindPaymentExchange(Queue paymentQueue, TopicExchange paymentExchange) {
        // #号可以向后匹配多层路径
        return BindingBuilder.bind(paymentQueue).to(paymentExchange).with("api.payment.#");
    }

    // ===============topic Exchange主题交换机队列========== **/

    /** ===============fanout Exchange扇形交换机队列========== **/

    @Bean
    public Queue reportPaymentQueue() {
        return new Queue("api.report.payment");
    }

    @Bean
    public Queue reportRefundQueue() {
        return new Queue("api.report.refund");
    }

    @Bean
    public FanoutExchange reportExchange() {
        return new FanoutExchange("reportExchange");
    }

    @Bean
    public Binding bindReportPaymentExchange(Queue reportPaymentQueue, FanoutExchange reportExchange) {
        return BindingBuilder.bind(reportPaymentQueue).to(reportExchange);
    }

    @Bean
    public Binding bindReportRefundExchange(Queue reportRefundQueue, FanoutExchange reportExchange) {
        return BindingBuilder.bind(reportRefundQueue).to(reportExchange);
    }

    // ===============famout Exchange扇形交换机队列========== **/

    /** ===============header Exchange扇形交换机队列========== **/

    @Bean
    public Queue creditBankQueue() {
        return new Queue("credit.bank");
    }

    @Bean
    public Queue creditFinanceQueue() {
        return new Queue("credit.finance");
    }

    @Bean
    public HeadersExchange creditBankExchange() {
        return new HeadersExchange("creditBankExchange");
    }

    @Bean
    public HeadersExchange creditFinanceExchange() {
        return new HeadersExchange("creditFinanceExchange");
    }

    @Bean
    public Binding bindCreditBankExchange(Queue creditBankQueue, HeadersExchange creditBankExchange) {
        Map<String, Object> headerMap = new HashMap<>();
        headerMap.put("type", "cash");
        headerMap.put("aging", "fast");
        return BindingBuilder.bind(creditBankQueue).to(creditBankExchange).whereAll(headerMap).match();
    }

    @Bean
    public Binding bindCreditFinanceExchange(Queue creditFinanceQueue, HeadersExchange creditFinanceExchange) {
        Map<String, Object> headerMap = new HashMap<>();
        headerMap.put("type", "cash");
        headerMap.put("aging", "fast");
        return BindingBuilder.bind(creditFinanceQueue).to(creditFinanceExchange).whereAny(headerMap).match();
    }

    // ===============fanout Exchange扇形交换机队列========== **/
} 

