package com.lockie.order.mq;

import org.redisson.api.RBlockingQueue;
import org.redisson.api.RDelayedQueue;
import org.redisson.api.RedissonClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

/**
 * @author: 邹细良
 * @Date: 2020/7/6 17:17
 * @Description:
 */
@Component
public class RedisDelayQueue {
    private static Logger logger = LoggerFactory.getLogger(RedisDelayQueue.class);

    @Autowired
    RedissonClient redissonClient;

    public <T> void addQueue(T t, long delay, TimeUnit timeUnit, String queueName) {
        logger.info("添加队列{}, dealy:{}, timeUnit:{} ",queueName,delay, timeUnit);
        RBlockingQueue<Object> blockingQueue = redissonClient.getBlockingQueue(queueName);
        RDelayedQueue<Object> delayedQueue = redissonClient.getDelayedQueue(blockingQueue);
        delayedQueue.offer(t, delay, timeUnit);
        delayedQueue.destroy();
    }
}
