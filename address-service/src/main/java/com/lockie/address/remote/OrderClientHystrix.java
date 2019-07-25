package com.lockie.address.remote;

import com.lockie.common.enums.ApiMsgEnum;
import com.lockie.common.vo.BaseReturnVO;
import org.springframework.stereotype.Component;

/**
 * 类或方法的功能描述 : 订单服务调用熔断
 *
 * @author: logan.zou
 * @date: 2018-06-21 10:28
 */
@Component
public class OrderClientHystrix implements OrderClient {
    @Override
    public String getPort() {
        return "order service 调用失败！";
    }

    @Override
    public BaseReturnVO getOrderById(Integer id) {
        BaseReturnVO baseReturnVO = new BaseReturnVO();
        baseReturnVO.setResCode(ApiMsgEnum.INTERNAL_SERVER_ERROR.getResCode());
        baseReturnVO.setResDes("order service getOrderById 调用失败！");
        return baseReturnVO;
    }
}

