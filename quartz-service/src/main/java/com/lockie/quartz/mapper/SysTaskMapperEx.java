package com.lockie.quartz.mapper;

import com.lockie.quartz.model.SysTask;

import java.util.List;

/**
 * @author: lockie
 * @Date: 2019/7/12 14:03
 * @Description:
 */
public interface SysTaskMapperEx {
    List<SysTask> findEnableTask(Integer isEnable);
}
