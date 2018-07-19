package com.sunvalley.demo.service;

import com.alibaba.druid.util.StringUtils;
import com.sunvalley.demo.mapper.ShopOrderMapper;
import com.sunvalley.demo.model.ShopOrder;
import com.sunvalley.demo.model.ShopOrderExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 类或方法的功能描述 : 订单服务
 *
 * @author: logan.zou
 * @date: 2018-07-13 11:17
 */
@Service
public class OrderService {

    @Autowired
    ShopOrderMapper shopOrderMapper;

    /**
     * 根据订单ID查询
     * @param id
     * @return
     */
    public ShopOrder getOrderById(Integer id) {
        return shopOrderMapper.selectByPrimaryKey(id);
    }

    /**
     * 根据订单号查询
     * @param orderNo
     * @return
     */
    public ShopOrder getOrderByOrderNo(String orderNo) {
        if (StringUtils.isEmpty(orderNo)) {
            return null;
        }
        ShopOrderExample shopOrderExample = new ShopOrderExample();
        shopOrderExample.createCriteria().andOrderNoEqualTo(orderNo);
        List<ShopOrder> list = shopOrderMapper.selectByExample(shopOrderExample);
        if (null != list && list.size() > 0) {
            return list.get(0);
        } else {
            return null;
        }
    }
} 

