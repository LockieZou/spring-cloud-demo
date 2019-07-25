package com.lockie.user.mq;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

/**
 * @author: 邹细良
 * @Date: 2019/4/29 15:50
 * @Description:
 */
@Component
public class LoginConsumer {
    private static final Logger log = LoggerFactory.getLogger(LoginConsumer.class);

    @RabbitListener(queues = "${log.user.queue.name}", containerFactory = "singleListenerContainer")
    public void consumerUserLogQueue(@Payload byte[] message) {
        try {
            String str = new String(message);
            log.info("用户登录mq消息：{}", str);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
