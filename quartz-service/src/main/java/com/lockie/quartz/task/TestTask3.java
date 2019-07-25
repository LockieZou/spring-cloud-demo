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
public class TestTask3 implements Job {
    Logger logger = LoggerFactory.getLogger(TestTask3.class);

    public TestTask3() {
    }

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        logger.info("testTask3 执行了 " + jobExecutionContext.getJobDetail().getKey());
    }
}
