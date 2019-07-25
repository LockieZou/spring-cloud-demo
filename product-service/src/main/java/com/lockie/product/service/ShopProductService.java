package com.lockie.product.service;

import com.lockie.product.mapper.ShopProductMapper;
import com.lockie.product.model.ShopProduct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 类或方法的功能描述 : 商品接口服务
 *
 * @author: logan.zou
 * @date: 2018-08-10 12:18
 */
@Slf4j
@Service
public class ShopProductService {
    @Autowired
    ShopProductMapper shopProductMapper;

    /**
     * 根据ID查询
     * @param id
     * @return
     */
    public ShopProduct getShopProductById(Integer id) {
        return shopProductMapper.selectByPrimaryKey(id);
    }
} 

