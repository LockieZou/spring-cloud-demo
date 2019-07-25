package com.lockie.common.enums;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 类或方法的功能描述 :TODO
 *
 * @author: logan.zou
 * @date: 2018-06-25 17:19
 */
public enum ApiMsgEnum {
    OK(200, "OK"),
    BAD_REQUEST(400, "Bad Request"),
    NOT_FOUND(404, "Not Found"),
    UNAUTHORIZED(401, "Unauthorized"),
    INTERNAL_SERVER_ERROR(500, "Internal Server Error"),
    BAD_GATEWAY(502, "Bad Gateway"),
    SERVICE_UNAVAILABLE(503, "Service Unavailable"),
    GATEWAY_TIMEOUT(504, "Gateway Timeout"),
    COMMON_SERVER_ERROR(10000, "COMMON_SERVER_ERROR"),
    USER_SERVER_ERROR(11000, "USER_SERVER_ERROR"),
    USER_REDIS_ERROR(11001, "USER_REDIS_ERROR"),
    PRODUCT_SERVER_ERROR(12000, "PRODUCT_SERVER_ERROR"),
    PRODUCT_PARAMETER_ERROR(12001, "PRODUCT_PARAMETER_ERROR"),
    PRODUCT_BOM_ERROR(12002, "PRODUCT_SERVER_ERROR"),
    ORDER_SERVER_ERROR(13000, "ORDER_SERVER_ERROR"),
    CUSTOMER_SERVER_ERROR(13000, "CUSTOMER_SERVER_ERROR");

    private int resCode;
    private String resDes;
    public static Map<Integer, String> apiMsgMap = new HashMap();

    private ApiMsgEnum(int code, String msg) {
        this.resCode = code;
        this.resDes = msg;
    }

    private static Map<Integer, String> getAll() {
        Map<Integer, String> retMap = new LinkedHashMap();
        ApiMsgEnum[] enumArr = values();
        ApiMsgEnum[] var2 = enumArr;
        int var3 = enumArr.length;

        for(int var4 = 0; var4 < var3; ++var4) {
            ApiMsgEnum aEnum = var2[var4];
            retMap.put(aEnum.getResCode(), aEnum.getResDes());
        }

        return retMap;
    }

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

    static {
        apiMsgMap = getAll();
    }
}