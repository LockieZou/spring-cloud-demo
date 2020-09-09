package com.lockie.order.mq;

import org.redisson.Redisson;
import org.redisson.api.RBlockingQueue;
import org.redisson.api.RDelayedQueue;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.TimeUnit;

/**
 * @author: 邹细良
 * @Date: 2020/7/8 11:11
 * @Description:
 */
public class RedPacketDealyQueue {
    private static final Logger LOGGER = LoggerFactory.getLogger(RedPacketDealyQueue.class);

    public static void main(String[] args) throws Exception {
        Config config = new Config();
        config.useSingleServer()
                .setAddress("redis://127.0.0.1:6379")
                .setPassword("123456");
        RedissonClient redissonClient = Redisson.create(config);
        /**
         * 红包队列
         */
        RBlockingQueue<RedPacketMessage> blockingQueue = redissonClient.getBlockingQueue("redPacketDelayQueue");
        /**
         * 定时任务将到期的元素转移到目标队列
         */
        RDelayedQueue<RedPacketMessage> delayedQueue = redissonClient.getDelayedQueue(blockingQueue);

        /**
         * 演示消息入队列
         */
        delayedQueue.offer(new RedPacketMessage(20200708001L),3, TimeUnit.SECONDS);
        delayedQueue.offer(new RedPacketMessage(20200708002L),10, TimeUnit.SECONDS);
        delayedQueue.offer(new RedPacketMessage(20200708003L),20, TimeUnit.SECONDS);

        /**
         * 取出失效红包
         */
        while (true) {
            RedPacketMessage message = blockingQueue.take();
            LOGGER.info("红包ID：{}过期失效了", message.getRedPacketId());
        }
    }
}
