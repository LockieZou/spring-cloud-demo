package com.lockie.quartz.task;

import org.quartz.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.Serializable;

/**
 * @author: 邹细良
 * @Date: 2019/7/12 14:59
 * @Description: DisallowConcurrentExecution 告诉Quartz不要并发地执行同一个job定义（这里指特定的job类）的多个实例
 * @Description: PersistJobDataAfterExecution 告诉Quartz在成功执行了job类的execute方法后（没有发生任何异常），更新JobDetail中JobDataMap的数据，
 *  使得该job（即JobDetail）在下一次执行的时候，JobDataMap中是更新后的数据，而不是更新前的旧数据
 */
@PersistJobDataAfterExecution
@DisallowConcurrentExecution
public class TestTask1 implements Job, Serializable {
    Logger logger = LoggerFactory.getLogger(TestTask1.class);

    @Autowired
    Scheduler scheduler;

    /**
     * 需要一个空的构造方法
     */
    public TestTask1() {
    }

    @Override
    public void execute(JobExecutionContext jobExecutionContext)
    {
        logger.info("testTask1 执行了 " + jobExecutionContext.getJobDetail().getKey());
    }
}
