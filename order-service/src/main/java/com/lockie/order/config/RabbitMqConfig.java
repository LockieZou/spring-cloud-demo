package com.lockie.order.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.support.CorrelationData;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.amqp.SimpleRabbitListenerContainerFactoryConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

/**
 * 类或方法的功能描述 : rabbitmq 配置类
 *
 * @author: logan.zou
 * @date: 2018-08-03 11:40
 */
@Slf4j
@Configuration
public class RabbitMqConfig {
    @Autowired
    CachingConnectionFactory connectionFactory;
    @Autowired
    SimpleRabbitListenerContainerFactoryConfigurer factoryConfigurer;

    private static final String REGISTER_EXCHANGE = "user.register.exchange";
    private static final String REGISTER_QUEUE = "user.register.queue";
    private static final String DELAY_EXCHANGE = "user.delay.exchange";
    private static final String DEALY_QUEUE = "user.delay.queue";

    /** 延迟队列配置开始 **/

    @Bean(name = "registerDelayQueue")
    public Queue registerDelayQueue() {
        Map<String, Object> params = new HashMap<>();
        params.put("x-dead-letter-exchange", DELAY_EXCHANGE);
        params.put("x-dead-letter-routing-key", "all");
        return new Queue(DEALY_QUEUE, true, false, false, params);
    }

    @Bean
    public DirectExchange registerDelayExchange() {
        return new DirectExchange(DELAY_EXCHANGE);
    }

    @Bean
    public Binding registerDelayBinding() {
        return BindingBuilder.bind(registerDelayQueue()).to(registerDelayExchange()).with("");
    }

    /** 延迟队列配置结束 **/


    /** 指标消费队列配置开始 **/

    @Bean
    public TopicExchange registerTopicExchange() {
        return new TopicExchange(REGISTER_EXCHANGE);
    }

    @Bean
    public Queue registerQueue() {
        return new Queue(REGISTER_QUEUE, true);
    }

    @Bean
    public Binding registerBinding() {
        return BindingBuilder.bind(registerQueue()).to(registerTopicExchange()).with("all");
    }

    /** 指标消费队列配置结束 **/


    /**
     * 单一消费者
     * @return
     */
    @Bean(name = "singleListenerContainer")
    public SimpleRabbitListenerContainerFactory listenerContainer() {
        SimpleRabbitListenerContainerFactory factory = new SimpleRabbitListenerContainerFactory();
        factory.setConnectionFactory(connectionFactory);
        factory.setMessageConverter(new Jackson2JsonMessageConverter());
        factory.setConcurrentConsumers(1);
        factory.setMaxConcurrentConsumers(1);
        factory.setPrefetchCount(1);
        factory.setTxSize(1);
        return factory;
    }

    /**
     * 多个消费者
     * @return
     */
    @Bean(name = "multiListenerContainer")
    public SimpleRabbitListenerContainerFactory multiListenerContainer() {
        SimpleRabbitListenerContainerFactory factory = new SimpleRabbitListenerContainerFactory();
        factoryConfigurer.configure(factory, connectionFactory);
        factory.setMessageConverter(new Jackson2JsonMessageConverter());
        factory.setAcknowledgeMode(AcknowledgeMode.NONE);
        return factory;
    }

    @Bean
    public RabbitTemplate rabbitTemplate() {
        connectionFactory.setPublisherConfirms(true);
        connectionFactory.setPublisherReturns(true);
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMandatory(true);
        rabbitTemplate.setConfirmCallback(new RabbitTemplate.ConfirmCallback() {
            @Override
            public void confirm(CorrelationData correlationData, boolean ack, String cause) {
                log.info("消息发送成功:correlationData({}),ack({}),cause({})",correlationData,ack,cause);            }
        });
        rabbitTemplate.setReturnCallback((message, replyCode, replyText, exchange, routingKey) ->
                log.info("消息丢失:exchange({}),route({}),replyCode({}),replyText({}),message:{}",
                        exchange, routingKey, replyCode, replyText, message));
        return rabbitTemplate;
    }
} 

