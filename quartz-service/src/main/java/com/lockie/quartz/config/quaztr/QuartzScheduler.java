package com.lockie.quartz.config.quaztr;

import org.quartz.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import java.util.Date;

/**
 * @author: 邹细良
 * @Date: 2019/7/11 11:04
 * @Description:
 */
@Configuration
public class QuartzScheduler {
    Logger logger = LoggerFactory.getLogger(QuartzScheduler.class);

    @Autowired
    private Scheduler scheduler;

    /**
     * 根据job名称获取job信息
     * @param jobName
     * @return
     * @throws SchedulerException
     */
    public String getJobInfo(String jobName) throws SchedulerException {
        TriggerKey triggerKey = new TriggerKey(jobName, Scheduler.DEFAULT_GROUP);
        CronTrigger cronTrigger = (CronTrigger) scheduler.getTrigger(triggerKey);
        return String.format("time:%s, state:%s", cronTrigger.getCronExpression(), scheduler.getTriggerState(triggerKey).name());
    }

    /**
     * 修改某个任务的执行时间
     * @param jobName
     * @param time
     * @return
     * @throws SchedulerException
     */
    public boolean modifyJob(String jobName, String time) throws SchedulerException {
        Date date = null;
        TriggerKey triggerKey = new TriggerKey(jobName, Scheduler.DEFAULT_GROUP);
        CronTrigger cronTrigger = (CronTrigger) scheduler.getTrigger(triggerKey);
        String oldTime = cronTrigger.getCronExpression();
        if (!oldTime.equalsIgnoreCase(time)) {
            CronScheduleBuilder cronScheduleBuilder = CronScheduleBuilder.cronSchedule(time);
            CronTrigger trigger = TriggerBuilder.newTrigger().withIdentity(jobName, Scheduler.DEFAULT_RECOVERY_GROUP)
                    .withSchedule(cronScheduleBuilder).build();
            date = scheduler.rescheduleJob(triggerKey, trigger);
        }
        return date != null;
    }

    /**
     * 暂定所有的任务
     * @throws SchedulerException
     */
    public void pauseAllJob() throws SchedulerException {
        scheduler.pauseAll();
    }

    /**
     * 暂定某个任务
     * @param jobName
     * @throws SchedulerException
     */
    public void pauseJob(String jobName) throws SchedulerException {
        JobKey jobKey = new JobKey(jobName, Scheduler.DEFAULT_GROUP);
        JobDetail jobDetail = scheduler.getJobDetail(jobKey);
        if (jobDetail == null) {
            return;
        }
        scheduler.pauseJob(jobKey);
    }

    /**
     * 恢复所有的任务
     * @throws SchedulerException
     */
    public void resumeAllJob() throws SchedulerException {
        scheduler.resumeAll();
    }

    /**
     * 恢复某个任务
     * @param jobName
     * @throws SchedulerException
     */
    public void resumeJob(String jobName) throws SchedulerException {
        JobKey jobKey = new JobKey(jobName, Scheduler.DEFAULT_GROUP);
        JobDetail jobDetail = scheduler.getJobDetail(jobKey);
        if (jobDetail == null) {
            return;
        }
        scheduler.resumeJob(jobKey);
    }

    /**
     * 删除某个任务
     * @param jobName
     * @throws SchedulerException
     */
    public void deleteJob(String jobName) throws SchedulerException {
        JobKey jobKey = new JobKey(jobName, Scheduler.DEFAULT_GROUP);
        JobDetail jobDetail = scheduler.getJobDetail(jobKey);
        if (jobDetail == null) {
            return;
        }
        scheduler.deleteJob(jobKey);
    }

    /**
     * 添加定时器
     * @param jobName
     * @param time
     * @throws SchedulerException
     */
    public void addJob(String jobName, String time) throws SchedulerException {
        Class classes = null;
        try {
            classes = Class.forName(jobName);
        } catch (ClassNotFoundException e) {
            logger.error("添加定时任务异常", e);
        }
        JobDetail jobDetail = JobBuilder.newJob(classes).withIdentity(jobName, Scheduler.DEFAULT_GROUP).build();
        CronScheduleBuilder cronScheduleBuilder = CronScheduleBuilder.cronSchedule(time);
        CronTrigger cronTrigger = TriggerBuilder.newTrigger().withIdentity(jobName, Scheduler.DEFAULT_GROUP).withSchedule(cronScheduleBuilder).build();
        scheduler.scheduleJob(jobDetail, cronTrigger);
    }
}
