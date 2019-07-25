package com.lockie.quartz.config.quaztr;

import com.lockie.quartz.service.SysTaskService;
import com.lockie.quartz.model.SysTask;
import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.util.CollectionUtils;

import java.util.List;

/**
 * @author: 邹细良
 * @Date: 2019/7/12 13:57
 * @Description:
 */
@Configuration
public class ApplicationStartQuartzJob implements ApplicationListener<ContextRefreshedEvent> {
    Logger logger = LoggerFactory.getLogger(ApplicationStartQuartzJob.class);

    @Autowired
    private SysTaskService sysTaskService;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        // 查询启用的任务
        List<SysTask> sysTaskList = sysTaskService.findEnableTask(1);
        Scheduler scheduler = null;

        try {
            scheduler = getScheduler();
        } catch (SchedulerException e) {
            logger.error("getScheduler 异常", e);
        }

        if (!CollectionUtils.isEmpty(sysTaskList)) {
            for (SysTask sysTask : sysTaskList) {
                Class classs = null;
                try {
                    classs = Class.forName(sysTask.getJobClass());
                } catch (ClassNotFoundException e) {
                    logger.error("查询类异常", e);
                }

                JobDetail jobDetail = JobBuilder.newJob(classs).withIdentity(sysTask.getJobClass(), Scheduler.DEFAULT_GROUP).build();
                CronScheduleBuilder cronScheduleBuilder = CronScheduleBuilder.cronSchedule(sysTask.getCronExpression());
                CronTrigger cronTrigger = TriggerBuilder.newTrigger().withIdentity(sysTask.getJobClass(), Scheduler.DEFAULT_GROUP).withSchedule(cronScheduleBuilder).build();

                try {
                    scheduler.scheduleJob(jobDetail, cronTrigger);
                } catch (SchedulerException e) {
                    logger.error("任务执行异常", e);
                }
            }
        }

        try {
            scheduler.start();
        } catch (SchedulerException e) {
            logger.error("任务启动执行异常",e);
        }
    }

    @Bean
    public Scheduler getScheduler() throws SchedulerException {
        SchedulerFactory schedulerFactory = new StdSchedulerFactory();
        return schedulerFactory.getScheduler();
    }
}
