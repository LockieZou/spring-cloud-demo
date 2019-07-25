package com.lockie.quartz.service;

import com.lockie.quartz.model.SysTask;

import java.util.List;

/**
 * @author: 邹细良
 * @Date: 2019/7/12 13:58
 * @Description:
 */
public interface SysTaskService {
    /**
     * 查询启用的任务
     * @param isEnable
     * @return
     */
    List<SysTask> findEnableTask(Integer isEnable);
}
