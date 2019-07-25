package com.lockie.address.service;

import com.lockie.address.model.MongoShopAddress;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * 类或方法的功能描述 :使用mongodb 操作
 *
 * @author: logan.zou
 * @date: 2018-06-25 18:13
 */
@Service
public class MongoDBShopAddressService {
    @Autowired
    private MongoTemplate mongoTemplate;

    /**
     *  保存地址
     * @param mongoShopAddress
     */
    public void saveAddress(MongoShopAddress mongoShopAddress) {
        if (null == mongoShopAddress) {
            return;
        }
        mongoShopAddress.setCreateDate(new Date());
        mongoShopAddress.setUpdateDate(new Date());
        mongoTemplate.save(mongoShopAddress);
    }

    /**
     * 查询所有的
     * @return
     */
    public List<MongoShopAddress> findAll() {
        List<MongoShopAddress> list = mongoTemplate.findAll(MongoShopAddress.class);
        return list;
    }

    /**
     * 根据地址ID查询
     * @param id
     * @return
     */
    public MongoShopAddress getAddressById(String id) {
        if (null == id) {
            return null;
        }
        Query query = new Query(Criteria.where("_id").is(id));
        MongoShopAddress mongoShopAddress = mongoTemplate.findOne(query, MongoShopAddress.class);
        return mongoShopAddress;
    }


    /**
     * 根据订单ID查询
     * @param orderId
     * @return
     */
    public MongoShopAddress getAddressByOrderId(Integer orderId) {
        if (null == orderId) {
            return null;
        }
        Query query = new Query(Criteria.where("orderId").is(orderId));
        MongoShopAddress mongoShopAddress = mongoTemplate.findOne(query, MongoShopAddress.class);
        return mongoShopAddress;
    }

    /**
     * 更新地址
     * @param mongoShopAddress
     */
    public void updateAddress(MongoShopAddress mongoShopAddress) {
        if (null == mongoShopAddress) {
            return;
        }
        Query query = new Query(Criteria.where("_id").is(mongoShopAddress.getId()));
        Update update = new Update().set("telephone", mongoShopAddress.getTelephone())
                                    .set("address1", mongoShopAddress.getAddress1())
                                    .set("updateDate", new Date());
        // 更新查询返回结果集的第一条
        mongoTemplate.updateFirst(query, update, MongoShopAddress.class);
        // 更新查询返回结果集的所有
        // mongoTemplate.updateMulti(query, update, MongoShopAddress.class);
    }

    /**
     * 根据ID删除
     * @param mongoShopAddress
     */
    public void deleteAddress(MongoShopAddress mongoShopAddress) {
        if (null == mongoShopAddress) {
            return;
        }
        mongoTemplate.remove(mongoShopAddress);
    }
} 

