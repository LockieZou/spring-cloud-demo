package com.lockie.order.mq;

/**
 * @author: 邹细良
 * @Date: 2020/7/6 17:10
 * @Description:
 */
public interface RedisDelayedQueueListener<T> {
    void invoke(T t);
}
