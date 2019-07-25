package com.lockie.address.service;

import com.lockie.address.model.ShopAddressExample;
import com.lockie.address.model.ShopAddress;
import com.lockie.address.mapper.ShopAddressMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * 类或方法的功能描述 :TODO
 *
 * @author: logan.zou
 * @date: 2018-06-25 16:23
 */
@Slf4j
@Service
public class ShopAddressService {
    @Autowired
    private ShopAddressMapper shopAddressMapper;

    /**
     * 根据地址ID查询
     * @param id
     * @return
     */
    public ShopAddress selectByPrimaryKey(Integer id) {
        if (null == id) {
            return null;
        }
        return shopAddressMapper.selectByPrimaryKey(id);
    }

    /**
     * 获取所有的地址
     * @return
     */
    public List<ShopAddress> findAll() {
        ShopAddressExample example = new ShopAddressExample();
        return shopAddressMapper.selectByExample(example);
    }

    /**
     * 根据订单ID查询
     * @param orderId
     * @return
     */
    public ShopAddress getAddressByOrderId(Integer orderId) {
        if (null == orderId) {
            return null;
        }

        ShopAddressExample example = new ShopAddressExample();
        example.createCriteria().andOrderIdEqualTo(orderId);
        List<ShopAddress> list = shopAddressMapper.selectByExample(example);
        if (null != list && list.size() > 0) {
            return list.get(0);
        } else {
            return null;
        }
    }

    /**
     * 地址保存
     * @param shopAddress
     * @return
     */
    public ShopAddress insertAddress(ShopAddress shopAddress) {
        if (null == shopAddress) {
            return null;
        }
        shopAddress.setCreateDate(new Date());
        shopAddress.setUpdateDate(new Date());
        int i = shopAddressMapper.insert(shopAddress);
        if (i > 0) {
            return shopAddressMapper.selectByPrimaryKey(shopAddress.getId());
        } else {
            return null;
        }
    }
} 

