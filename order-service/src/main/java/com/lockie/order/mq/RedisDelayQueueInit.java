package com.lockie.order.mq;

import com.alibaba.fastjson.JSON;
import org.redisson.api.RBlockingQueue;
import org.redisson.api.RedissonClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @author: 邹细良
 * @Date: 2020/7/6 17:09
 * @Description:
 */
@Component
public class RedisDelayQueueInit implements ApplicationContextAware {
    private static Logger logger = LoggerFactory.getLogger(RedisDelayQueueInit.class);
    @Autowired
    RedissonClient redissonClient;

    /**
     * 获取应用上下文并获取相应的接口实现类
     * @param applicationContext
     * @throws BeansException
     */
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        Map<String, RedisDelayedQueueListener> map = applicationContext.getBeansOfType(RedisDelayedQueueListener.class);
        map.entrySet().forEach(entry -> {
            String listenerName = entry.getValue().getClass().getName();
            startThread(listenerName,entry.getValue());
        });
    }

    /**
     * 启动线程获取队列
     * @param queueName
     * @param listener
     * @param <T>
     */
    private <T> void startThread(String queueName, RedisDelayedQueueListener listener) {
        RBlockingQueue<T> blockingQueue = redissonClient.getBlockingQueue(queueName);
        //由于此线程需要常驻，可以新建线程，不用交给线程池管理
        Thread thread = new Thread(() -> {
            while (true) {
                try {
                    T t = blockingQueue.take();
                    logger.info("监听队列线程{},获取到值:{}", queueName, JSON.toJSONString(t));
                } catch (Exception e) {
                    logger.error("监听队列线程错误,", e);
                    try {
                        Thread.sleep(10000);
                    } catch (InterruptedException EX) {

                    }
                }
            }
        });

        thread.setName(queueName);
        thread.start();
    }
}
