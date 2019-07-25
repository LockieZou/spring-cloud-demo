package com.lockie.quartz.task;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author: 邹细良
 * @Date: 2019/7/12 14:59
 * @Description:
 */
public class TestTask2 implements Job {
    Logger logger = LoggerFactory.getLogger(TestTask2.class);

    /**
     * 需要一个空的构造方法
     */
    public TestTask2() {
    }

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        logger.info("testTask2 执行了 " + jobExecutionContext.getJobDetail().getKey());
    }
}
