package com.lockie.common.Exception;

import com.lockie.common.enums.ApiMsgEnum;

/**
 * 类或方法的功能描述 :TODO
 *
 * @author: logan.zou
 * @date: 2018-06-25 17:21
 */
public class BusinessException extends RuntimeException implements CommonException {
    private static final long serialVersionUID = 2332608236621015982L;
    private int code;
    private Throwable throwable;

    public BusinessException() {
    }

    public BusinessException(String message) {
        super(message);
    }

    public BusinessException(int code, String message) {
        super(message);
        this.code = code;
    }

    public BusinessException(Throwable cause) {
        super(cause);
        this.throwable = cause;
    }

    public BusinessException(String message, Throwable cause) {
        super(message, cause);
        this.throwable = cause;
    }

    public BusinessException(int code, String message, Throwable cause) {
        super(message, cause);
        this.code = code;
        this.throwable = cause;
    }

    public BusinessException(ApiMsgEnum apiMsgEnum, String message) {
        super(message);
        this.code = apiMsgEnum.getResCode();
    }

    public BusinessException(ApiMsgEnum apiMsgEnum) {
        super(apiMsgEnum.getResDes());
        this.code = apiMsgEnum.getResCode();
    }

    public void setCode(int code) {
        this.code = code;
    }

    public int getCode() {
        return this.code;
    }

    public Throwable getThrowable() {
        return this.throwable;
    }
} 

