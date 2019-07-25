package com.lockie.order.util;

import org.apache.commons.lang.StringUtils;

/**
 * 类或方法的功能描述 :订单号生成
 *
 * @author: logan.zou
 * @date: 2018-03-19 10:54
 */
public class OrderNoUtil {
    // 订单中用户ID的长度
    private final static int length = 4;
    // 随机数位数
    private final static int randomLength = 3;

    /**
     * 生成订单号
     * @param userId
     * @return
     */
    public static String getOrderNo(String productType, String purchaseType, Integer userId) throws Exception {
        if (null == userId || StringUtils.isEmpty(productType) || StringUtils.isEmpty(purchaseType)) {
            return "参数为空";
        }
        // 用户ID
        String userIdStr = userId.toString();
        int userIdLength = userIdStr.length();

        String orderUserId = "";
        // 用户ID转换，如果大于4位则截取用户ID后四位，不足4位的前面补0
        if (userIdLength > length) {
            orderUserId = userIdStr.substring(userIdLength - length, userIdLength);
        } else {
            String format = "%0" + length + "d";
            orderUserId = String.format(format, userId);
        }
        if (StringUtils.isEmpty(orderUserId)) {
            throw new Exception();
        }

        // 三位随机数
        String randomNo = RandomUtil.getNumForStr(randomLength);
        if (StringUtils.isEmpty(randomNo)) {
            throw new Exception();
        }

        // 当前时间的时间戳
        Long time = System.currentTimeMillis() / 1000;
        if (null == time) {
            throw new Exception();
        }

        return productType + purchaseType + time + randomNo + orderUserId;
    }
}

