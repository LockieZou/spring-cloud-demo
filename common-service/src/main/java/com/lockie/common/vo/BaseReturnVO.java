package com.lockie.common.vo;

import com.lockie.common.Exception.CommonException;
import com.lockie.common.enums.ApiMsgEnum;

import java.io.Serializable;

/**
 * 类或方法的功能描述 :TODO
 *
 * @author: logan.zou
 * @date: 2018-06-25 17:23
 */
public class BaseReturnVO implements Serializable {
    protected int resCode;
    protected String resDes;
    protected Object data;

    public int getResCode() {
        return this.resCode;
    }

    public void setResCode(int resCode) {
        this.resCode = resCode;
    }

    public String getResDes() {
        return this.resDes;
    }

    public void setResDes(String resDes) {
        this.resDes = resDes;
    }

    public Object getData() {
        return this.data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public BaseReturnVO() {
    }

    public BaseReturnVO(int code, String msg) {
        this.resCode = code;
        this.resDes = msg;
        this.data = "";
    }

    public BaseReturnVO(int code, Exception e) {
        this.resCode = code;
        this.resDes = e.getMessage();
        this.data = "";
    }

    public BaseReturnVO(int code, String msg, Exception e) {
        this.resCode = code;
        this.resDes = msg;
        this.data = "";
    }

    public BaseReturnVO(Object data) {
        this.resCode = ApiMsgEnum.OK.getResCode();
        this.resDes = ApiMsgEnum.OK.getResDes();
        this.data = data;
    }

    public BaseReturnVO(CommonException exp) {
        this.resCode = exp.getCode();
        this.resDes = exp.getMessage();
        this.data = "";
    }

    public BaseReturnVO(ApiMsgEnum msgEnum) {
        this.resCode = msgEnum.getResCode();
        this.resDes = msgEnum.getResDes();
        this.data = "";
    }
}


