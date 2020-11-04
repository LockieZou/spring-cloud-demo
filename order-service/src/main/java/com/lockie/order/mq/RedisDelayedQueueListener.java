package com.lockie.order.mq;

/**
 * @author: lockie
 * @Date: 2020/7/6 17:10
 * @Description:
 */
public interface RedisDelayedQueueListener<T> {
    void invoke(T t);
}
