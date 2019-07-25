package com.lockie.common.model;

import java.io.Serializable;
import java.util.Date;

/**
 * 类或方法的功能描述 :实体基类
 *
 * @author: wesley.wang
 * @date: 2018-03-14 10:28
 */
public class BaseModel implements Serializable{

    /**
     * 数据库主键
     */
    private Integer id;
    /**
     * 创建时间日期类型
     */
    private Date createDate;
    /**
     * 创建人
     */
    private Integer createBy;
    /**
     * 修改时间日期类型
     */
    private Date updateDate;
    /**
     * 更新人
     */
    private Integer updateBy;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Integer getCreateBy() {
        return createBy;
    }

    public void setCreateBy(Integer createBy) {
        this.createBy = createBy;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    public Integer getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(Integer updateBy) {
        this.updateBy = updateBy;
    }
}

