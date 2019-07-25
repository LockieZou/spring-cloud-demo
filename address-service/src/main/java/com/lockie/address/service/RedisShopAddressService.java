package com.lockie.address.service;

import com.alibaba.fastjson.JSON;
import com.lockie.address.mapper.ShopAddressMapper;
import com.lockie.address.model.ShopAddress;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * 类或方法的功能描述 : redis接口实现类
 *
 * @author: logan.zou
 * @date: 2018-07-16 17:20
 */
@Service
public class RedisShopAddressService {
    @Autowired
    ShopAddressMapper shopAddressMapper;

    /**
     * @Cacheable  缓存的入口，首先检查缓存如果没有命中则执行方法并将方法结果缓存

        @CacheEvict  缓存回收，清空对应的缓存数据

        @CachePut   缓存更新，执行方法并将方法执行结果更新到缓存中

        @Caching    组合多个缓存操作

        @CacheConfig 类级别的公共配置
     */
    // 第一次执行的时候检测到缓存中没有数据，就会从数据库中获取，第二次检测到缓存中有就会从缓存中获取
    @Cacheable(value = "shopAddress", key = "'shopAddress'+#id")
    public ShopAddress getRedisAddressById(Integer id) {
        ShopAddress address = shopAddressMapper.selectByPrimaryKey(id);
        System.out.println("数据库中获取数据：" + JSON.toJSONString(address));
        return address;
    }

    @CacheEvict(cacheNames = "shopAddress", key = "('shopAddress').concat(#shopAddress.id)")
    public ShopAddress saveAddress(ShopAddress shopAddress) {
        shopAddress.setCreateDate(new Date());
        shopAddressMapper.insert(shopAddress);
        return shopAddress;
    }

    @Caching(evict = {
            @CacheEvict(cacheNames="shopAddress", key="('shopAddress').concat(#shopAddress.id)"),
            @CacheEvict(cacheNames="shopAddress", key="('shopAddress2').concat(#shopAddress.email)")
    })
    public ShopAddress updateAddress(ShopAddress shopAddress) {
        shopAddress.setUpdateDate(new Date());
        shopAddressMapper.updateByPrimaryKey(shopAddress);
        return shopAddress;
    }
} 

