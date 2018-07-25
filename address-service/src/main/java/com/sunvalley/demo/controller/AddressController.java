package com.sunvalley.demo.controller;

import com.sunvalley.demo.enums.ApiMsgEnum;
import com.sunvalley.demo.model.MongoShopAddress;
import com.sunvalley.demo.model.ShopAddress;
import com.sunvalley.demo.remote.OrderClient;
import com.sunvalley.demo.remote.OrderRibbonClient;
import com.sunvalley.demo.service.RabbitMqSender;
import com.sunvalley.demo.service.MongoDBShopAddressService;
import com.sunvalley.demo.service.RedisShopAddressService;
import com.sunvalley.demo.service.ShopAddressService;
import com.sunvalley.demo.vo.BaseReturnVO;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.validator.constraints.SafeHtml;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

/**
 * 类或方法的功能描述 :TODO
 *
 * @author: logan.zou
 * @date: 2018-06-22 16:17
 */
@Slf4j
@RestController
@RequestMapping("/address")
public class AddressController {
    @Value("${server.port}")
    private String port;
    @Autowired
    ShopAddressService shopAddressService;
    @Autowired
    OrderClient orderClient;
    @Autowired
    OrderRibbonClient orderRibbonClient;
    @Autowired
    MongoDBShopAddressService mongoDBShopAddressService;
    @Autowired
    RabbitMqSender rabbitMqSender;
    @Autowired
    RedisShopAddressService redisShopAddressService;



    /**
     * 获取服务端口号
     * @return
     */
    @GetMapping("/getPort")
    public String getPort() {
        log.info("address-service port：" + port);
        return "address-service port：" + port;
    }

    /**========================================= mysql 操作 ========================================================**/


    /**
     * 根据订单ID查询地址
     * @param orderId
     * @return
     */
    @GetMapping("/getAddressByOrderId")
    public BaseReturnVO getAddressByOrderId(Integer orderId) {
        if (null == orderId) {
            BaseReturnVO baseReturnVO = new BaseReturnVO();
            baseReturnVO.setResCode(ApiMsgEnum.COMMON_SERVER_ERROR.getResCode());
            baseReturnVO.setResDes("order id is empty!");
            return baseReturnVO;
        }
        try {
            ShopAddress shopAddress = shopAddressService.getAddressByOrderId(orderId);
            return new BaseReturnVO(shopAddress);
        } catch (Exception e) {
            log.error("getAddressByOrderId error!" + e.getMessage());
            BaseReturnVO baseReturnVO = new BaseReturnVO();
            baseReturnVO.setResCode(ApiMsgEnum.INTERNAL_SERVER_ERROR.getResCode());
            baseReturnVO.setResDes(ApiMsgEnum.INTERNAL_SERVER_ERROR.getResDes());
            return baseReturnVO;
        }
    }

    /**
     * 保存地址到数据库
     * @param shopAddress
     * @return
     */
    @PostMapping("/addAddress")
    public BaseReturnVO addAddress(@RequestBody ShopAddress shopAddress) {
        if (null == shopAddress) {
            BaseReturnVO baseReturnVO = new BaseReturnVO();
            baseReturnVO.setResCode(ApiMsgEnum.COMMON_SERVER_ERROR.getResCode());
            baseReturnVO.setResDes("shopAddress is empty!");
            return baseReturnVO;
        }
        try {
            ShopAddress returnAddress = shopAddressService.insertAddress(shopAddress);
            return new BaseReturnVO(returnAddress);
        } catch (Exception e) {
            log.error("addAddress error!" + e.getMessage());
            BaseReturnVO baseReturnVO = new BaseReturnVO();
            baseReturnVO.setResCode(ApiMsgEnum.INTERNAL_SERVER_ERROR.getResCode());
            baseReturnVO.setResDes(ApiMsgEnum.INTERNAL_SERVER_ERROR.getResDes());
            return baseReturnVO;
        }
    }

    /**
     * 使用feign调用 order-service 查询数据
     * @param id
     * @return
     */
    @GetMapping("/feign/getFeignOrderById/{id}")
    public BaseReturnVO getFeignOrderById(@PathVariable("id") Integer id) {
        if (null == id) {
            BaseReturnVO baseReturnVO = new BaseReturnVO();
            baseReturnVO.setResCode(ApiMsgEnum.COMMON_SERVER_ERROR.getResCode());
            baseReturnVO.setResDes("order id is empty!");
            return baseReturnVO;
        }
        try {
            BaseReturnVO baseReturnVO = orderClient.getOrderById(id);
            return baseReturnVO;
        } catch (Exception e) {
            log.error("getAddressByOrderId error!" + e.getMessage());
            BaseReturnVO baseReturnVO = new BaseReturnVO();
            baseReturnVO.setResCode(ApiMsgEnum.INTERNAL_SERVER_ERROR.getResCode());
            baseReturnVO.setResDes(ApiMsgEnum.INTERNAL_SERVER_ERROR.getResDes());
            return baseReturnVO;
        }
    }

    /**
     * 使用ribbon调用 order-service 的端口号测试负载均衡
     * 首先需要启动一个order-service服务，然后修改端口号后再次启动，原来不要关了，然后多次访问这个接口就会发现调用了不同的端口
     * @return
     */
    @GetMapping("/ribbon/getRibbonOrderPort")
    public String getRibbonOrderPort() {
        return orderRibbonClient.getPort();
    }

    /**
     * 使用ribbon调用 order-service 查询数据
     * @param id
     * @return
     */
    @GetMapping("/ribbon/getRibbonOrderById/{id}")
    public BaseReturnVO getRibbonOrderById(@PathVariable("id") Integer id) {
        if (null == id) {
            BaseReturnVO baseReturnVO = new BaseReturnVO();
            baseReturnVO.setResCode(ApiMsgEnum.COMMON_SERVER_ERROR.getResCode());
            baseReturnVO.setResDes("order id is empty!");
            return baseReturnVO;
        }
        try {
            BaseReturnVO baseReturnVO = orderRibbonClient.getOrderById(id);
            return baseReturnVO;
        } catch (Exception e) {
            log.error("getAddressByOrderId error!" + e.getMessage());
            BaseReturnVO baseReturnVO = new BaseReturnVO();
            baseReturnVO.setResCode(ApiMsgEnum.INTERNAL_SERVER_ERROR.getResCode());
            baseReturnVO.setResDes(ApiMsgEnum.INTERNAL_SERVER_ERROR.getResDes());
            return baseReturnVO;
        }
    }

    /**========================================= mysql 操作 ========================================================**/



    /**========================================= mongodb操作 ========================================================**/

    /**
     * 保存地址数据到mongodb
     * @param mongoShopAddress
     * @return
     */
    @PostMapping("/mongodb/saveAddress")
    public BaseReturnVO saveAddress(@RequestBody MongoShopAddress mongoShopAddress) {
        if (null == mongoShopAddress) {
            BaseReturnVO baseReturnVO = new BaseReturnVO();
            baseReturnVO.setResCode(ApiMsgEnum.COMMON_SERVER_ERROR.getResCode());
            baseReturnVO.setResDes("shopAddress is empty!");
            return baseReturnVO;
        }
        try {
            mongoDBShopAddressService.saveAddress(mongoShopAddress);
            return new BaseReturnVO(1);
        } catch (Exception e) {
            log.error("mongodb saveAddress error!" + e.getMessage());
            BaseReturnVO baseReturnVO = new BaseReturnVO();
            baseReturnVO.setResCode(ApiMsgEnum.INTERNAL_SERVER_ERROR.getResCode());
            baseReturnVO.setResDes(ApiMsgEnum.INTERNAL_SERVER_ERROR.getResDes());
            return baseReturnVO;
        }
    }

    /**
     * 更新地址到mongodb
     * @param mongoShopAddress
     * @return
     */
    @PostMapping("/mongodb/updateAddress")
    public BaseReturnVO updateAddress(@RequestBody MongoShopAddress mongoShopAddress) {
        if (null == mongoShopAddress) {
            BaseReturnVO baseReturnVO = new BaseReturnVO();
            baseReturnVO.setResCode(ApiMsgEnum.COMMON_SERVER_ERROR.getResCode());
            baseReturnVO.setResDes("shopAddress is empty!");
            return baseReturnVO;
        }
        try {
            mongoDBShopAddressService.updateAddress(mongoShopAddress);
            return new BaseReturnVO(1);
        } catch (Exception e) {
            log.error("mongodb updateAddress error!" + e.getMessage());
            BaseReturnVO baseReturnVO = new BaseReturnVO();
            baseReturnVO.setResCode(ApiMsgEnum.INTERNAL_SERVER_ERROR.getResCode());
            baseReturnVO.setResDes(ApiMsgEnum.INTERNAL_SERVER_ERROR.getResDes());
            return baseReturnVO;
        }
    }

    /**
     * 根据ID查询mongodb数据
     * @param orderId
     * @return
     */
    @GetMapping("/mongodb/getMongoAddressByOrderId")
    public BaseReturnVO getMongoAddressByOrderId(@RequestParam("orderId") Integer orderId) {
        if (null == orderId) {
            BaseReturnVO baseReturnVO = new BaseReturnVO();
            baseReturnVO.setResCode(ApiMsgEnum.COMMON_SERVER_ERROR.getResCode());
            baseReturnVO.setResDes("shopAddress orderId is empty!");
            return baseReturnVO;
        }
        try {
            MongoShopAddress mongoShopAddress = mongoDBShopAddressService.getAddressByOrderId(orderId);
            return new BaseReturnVO(mongoShopAddress);
        } catch (Exception e) {
            log.error("mongodb getMongoAddressByOrderId error!" + e.getMessage());
            BaseReturnVO baseReturnVO = new BaseReturnVO();
            baseReturnVO.setResCode(ApiMsgEnum.INTERNAL_SERVER_ERROR.getResCode());
            baseReturnVO.setResDes(ApiMsgEnum.INTERNAL_SERVER_ERROR.getResDes());
            return baseReturnVO;
        }
    }

    /**
     * 根据ID删除mongodb数据
     * @param id
     * @return
     */
    @PostMapping("/mongodb/deleteMongoAddressById")
    public BaseReturnVO deleteMongoAddressById(@RequestParam("id") String id) {
        if (null == id) {
            BaseReturnVO baseReturnVO = new BaseReturnVO();
            baseReturnVO.setResCode(ApiMsgEnum.COMMON_SERVER_ERROR.getResCode());
            baseReturnVO.setResDes("shopAddress id is empty!");
            return baseReturnVO;
        }
        try {
            // 先查询是否存在
            MongoShopAddress mongoShopAddress = mongoDBShopAddressService.getAddressById(id);
            if (null == mongoShopAddress) {
                BaseReturnVO baseReturnVO = new BaseReturnVO();
                baseReturnVO.setResCode(ApiMsgEnum.COMMON_SERVER_ERROR.getResCode());
                baseReturnVO.setResDes("shopAddress id is not exist!");
                return baseReturnVO;
            }
            // 删除
            mongoDBShopAddressService.deleteAddress(mongoShopAddress);
            return new BaseReturnVO(1);
        } catch (Exception e) {
            log.error("mongodb deleteMongoAddressById error!" + e.getMessage());
            BaseReturnVO baseReturnVO = new BaseReturnVO();
            baseReturnVO.setResCode(ApiMsgEnum.INTERNAL_SERVER_ERROR.getResCode());
            baseReturnVO.setResDes(ApiMsgEnum.INTERNAL_SERVER_ERROR.getResDes());
            return baseReturnVO;
        }
    }

    /**========================================= mongodb 操作 ========================================================**/


    /**========================================= rabbitmq 操作 ========================================================**/


    /**
     *  获取rabbitmq
     * @param id
     * @return
     */
    @GetMapping("/rabbitmq/getRabbitMqAddressById")
    public BaseReturnVO getRabbitMqAddressById(Integer id) {
        if (null == id) {
            BaseReturnVO baseReturnVO = new BaseReturnVO();
            baseReturnVO.setResCode(ApiMsgEnum.COMMON_SERVER_ERROR.getResCode());
            baseReturnVO.setResDes("shopAddress id is empty!");
            return baseReturnVO;
        }
        try {
            // 查询
            ShopAddress shopAddress = shopAddressService.selectByPrimaryKey(id);
            // 发送到rabbitmq队列中
            if (null != shopAddress && null != shopAddress.getId()) {
                rabbitMqSender.send(shopAddress);
            }

            return new BaseReturnVO(shopAddress);
        } catch (Exception e) {
            log.error("rabbit getRabbitMqAddressById error!" + e.getMessage());
            BaseReturnVO baseReturnVO = new BaseReturnVO();
            baseReturnVO.setResCode(ApiMsgEnum.INTERNAL_SERVER_ERROR.getResCode());
            baseReturnVO.setResDes(ApiMsgEnum.INTERNAL_SERVER_ERROR.getResDes());
            return baseReturnVO;
        }
    }

    /**
     * topic 交换机发送
     * @return
     */
    @GetMapping("/rabbitmq/topicSend")
    public BaseReturnVO topicSend() {
        try {
            ShopAddress shopAddress = shopAddressService.selectByPrimaryKey(1);
            // 1
            rabbitMqSender.saveAddress("core exchange");
            // 2
            rabbitMqSender.addressQuery("core exchange");
            // 3
            rabbitMqSender.order("payment exchange");
            // 4
            rabbitMqSender.orderQuery("payment exchange");
            // 5
            rabbitMqSender.orderDetailQuery("payment exchange");
            return new BaseReturnVO(shopAddress);
        } catch (Exception e) {
            log.error("rabbit topicSend error!" + e.getMessage());
            BaseReturnVO baseReturnVO = new BaseReturnVO();
            baseReturnVO.setResCode(ApiMsgEnum.INTERNAL_SERVER_ERROR.getResCode());
            baseReturnVO.setResDes(ApiMsgEnum.INTERNAL_SERVER_ERROR.getResDes());
            return baseReturnVO;
        }
    }

    /**========================================= rabbitmq 操作 ========================================================**/


    /**
     * ========================================= redis 操作 ========================================================
     **/
    @GetMapping("/redis/saveRedisAddress")
    public BaseReturnVO saveRedisAddress() {
        try {
            ShopAddress shopAddress = new ShopAddress();
            shopAddress.setOrderId(7);
            shopAddress.setEmail("logan.zou@sunvalley.com.cn");
            shopAddress.setFirstName("logan");
            shopAddress.setLastName("zou");
            shopAddress.setPostcode("51800");
            shopAddress.setFax("");
            shopAddress.setTelephone("188000001111");
            shopAddress.setCountryId(1);
            shopAddress.setRegionId(1);
            shopAddress.setCity("Los Angel");
            shopAddress.setAddress1("Holly Road");

            ShopAddress address = redisShopAddressService.saveAddress(shopAddress);
            return new BaseReturnVO(address);
        } catch (Exception e) {
            log.error("redis saveRedisAddress error!" + e.getMessage());
            BaseReturnVO baseReturnVO = new BaseReturnVO();
            baseReturnVO.setResCode(ApiMsgEnum.INTERNAL_SERVER_ERROR.getResCode());
            baseReturnVO.setResDes(ApiMsgEnum.INTERNAL_SERVER_ERROR.getResDes());
            return baseReturnVO;
        }
    }

    @GetMapping("/redis/getRedisAddress")
    public BaseReturnVO getRedisAddress(Integer id) {
        if (null == id) {
            BaseReturnVO baseReturnVO = new BaseReturnVO();
            baseReturnVO.setResCode(ApiMsgEnum.COMMON_SERVER_ERROR.getResCode());
            baseReturnVO.setResDes("shopAddress id is empty!");
            return baseReturnVO;
        }

        ShopAddress address = redisShopAddressService.getRedisAddressById(id);
        return new BaseReturnVO(address);
    }

    @GetMapping("/redis/updateRedisAddress")
    public BaseReturnVO updateRedisAddress(Integer id) {
        if (null == id) {
            BaseReturnVO baseReturnVO = new BaseReturnVO();
            baseReturnVO.setResCode(ApiMsgEnum.COMMON_SERVER_ERROR.getResCode());
            baseReturnVO.setResDes("shopAddress id is empty!");
            return baseReturnVO;
        }
        ShopAddress address = redisShopAddressService.getRedisAddressById(id);
        ShopAddress updateAddress = redisShopAddressService.updateAddress(address);
        return new BaseReturnVO(updateAddress);
    }


    /**========================================= redis 操作 ========================================================**/

} 

