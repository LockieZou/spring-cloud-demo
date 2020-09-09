package com.lockie.order;

import com.lockie.order.model.RedisDelay;
import com.lockie.order.mq.MyRedisDelayedQueueListener;
import com.lockie.order.mq.RedisDelayQueue;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.concurrent.TimeUnit;

@RunWith(SpringRunner.class)
@SpringBootTest
public class OrderServiceApplicationTests {

	@Test
	public void contextLoads() {
	}

	@Test
	public void testRedis() {
		RedisDelay redisDelay = new RedisDelay();
		redisDelay.setId(1L);
		redisDelay.setName("延迟队列，3秒后执行");

		RedisDelayQueue queue = new RedisDelayQueue();
		queue.addQueue(redisDelay, 10, TimeUnit.SECONDS, MyRedisDelayedQueueListener.class.getName());

	}

	public static void main(String[] args) {
		RedisDelay redisDelay = new RedisDelay();
		redisDelay.setId(1L);
		redisDelay.setName("延迟队列，3秒后执行");

		RedisDelayQueue queue = new RedisDelayQueue();
		queue.addQueue(redisDelay, 10, TimeUnit.SECONDS, MyRedisDelayedQueueListener.class.getName());
	}
}
