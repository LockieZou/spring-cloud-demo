package com.lockie.order.util;


import org.redisson.api.RLock;
import org.redisson.api.RMapCache;
import org.redisson.api.RedissonClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.TimeUnit;

/**
 * @author: lockie
 * @Date: 2020/7/3 13:45
 * @Description: 分布式锁redisson工具类
 */
public class RedissLockUtil {

    private static final Logger logger = LoggerFactory.getLogger(RedissLockUtil.class);

    private static RedissonClient redissonClient;
    private static final String CACHE_NAME = "skill";

    /**
     * 加锁
     * @param lockKey
     * @return
     */
    public static RLock lock(String lockKey) {
        RLock lock = redissonClient.getLock(lockKey);
        lock.lock();
        return lock;
    }

    /**
     * 释放锁
     * @param lockKey
     */
    public static void unlock(String lockKey) {
        RLock lock = redissonClient.getLock(lockKey);
        lock.unlock();
    }

    /**
     * 释放锁
     * @param rLock
     */
    public static void unlock(RLock rLock) {
        rLock.unlock();
    }

    /**
     * 带时间的锁
     * @param lockKey
     * @param timeOut 超时时间，单位秒
     * @return
     */
    public static RLock lock(String lockKey, int timeOut) {
        RLock lock = redissonClient.getLock(lockKey);
        lock.lock(timeOut, TimeUnit.SECONDS);
        return lock;
    }

    /**
     * 时间单位锁
     * @param lockKey
     * @param unit
     * @param timeOut
     * @return
     */
    public static RLock lock(String lockKey, TimeUnit unit, int timeOut) {
        RLock lock = redissonClient.getLock(lockKey);
        lock.lock(timeOut, unit);
        return lock;
    }

    /**
     * 加锁
     * @param lockKey
     * @param unit
     * @param waitTime
     * @param timeOut
     * @return
     */
    public boolean tryLock(String lockKey, TimeUnit unit, int waitTime, int timeOut) {
        RLock lock = redissonClient.getLock(lockKey);
        try {
            return lock.tryLock(waitTime, timeOut, unit);
        } catch (InterruptedException e) {
            logger.error("redis try lock fail" + e);
            return false;
        }
    }


    public void initCount(String key, int count) {
        RMapCache<String, Integer> mapCache = redissonClient.getMapCache(CACHE_NAME);
        mapCache.putIfAbsent(key, count, 3, TimeUnit.HOURS);
    }

    /**
     * 递增
     * @param key
     * @param incrNum 递增的数字
     * @return
     */
    public int incr(String key, int incrNum) {
        RMapCache<String, Integer> mapCache = redissonClient.getMapCache(CACHE_NAME);
        if (incrNum < 0) {
            throw new RuntimeException("递增因子必须大于0");
        }
        return mapCache.addAndGet(key, incrNum);
    }

    /**
     * 递减
     * @param key
     * @param decrNum
     * @return
     */
    public int decr(String key, int decrNum) {
        RMapCache<String, Integer> mapCache = redissonClient.getMapCache(CACHE_NAME);
        if (decrNum < 0) {
            throw new RuntimeException("递减因子必须大于0");
        }
        return mapCache.addAndGet(key, -decrNum);
    }

    public RedissonClient getRedissonClient() {
        return redissonClient;
    }

    public void setRedissonClient(RedissonClient client) {
        redissonClient = client;
    }
}
