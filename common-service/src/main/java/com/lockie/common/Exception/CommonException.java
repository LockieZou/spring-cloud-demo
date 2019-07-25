package com.lockie.common.Exception;

/**
 * 类或方法的功能描述 :TODO
 *
 * @author: logan.zou
 * @date: 2018-06-25 17:20
 */
public interface CommonException {
    int getCode();

    String getMessage();

    Throwable getThrowable();
}
