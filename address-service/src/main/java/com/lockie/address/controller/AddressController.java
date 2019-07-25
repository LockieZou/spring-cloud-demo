package com.lockie.address.controller;

import com.alibaba.fastjson.JSON;
import com.lockie.address.model.MongoShopAddress;
import com.lockie.address.model.ShopAddress;
import com.lockie.address.remote.OrderClient;
import com.lockie.address.remote.OrderRibbonClient;
import com.lockie.address.service.RabbitMqSender;
import com.lockie.address.service.RedisShopAddressService;
import com.lockie.address.service.ShopAddressService;
import com.lockie.common.enums.ApiMsgEnum;
import com.lockie.address.service.MongoDBShopAddressService;
import com.lockie.common.vo.BaseReturnVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.*;

/**
 * 类或方法的功能描述 : 地址接口服务
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
    @Autowired
    StringRedisTemplate stringRedisTemplate;
    @Autowired
    RedisTemplate redisTemplate;



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
            shopAddress.setEmail("logan.zou@lockie.com.cn");
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

    /** redis 操作字符串，集合List，set，Hash,zSet **/
    @PostMapping("/redis/setStringValue")
    public BaseReturnVO setStringValue(String key, String value) {
        stringRedisTemplate.opsForValue().set(key, value);
        return new BaseReturnVO(true);
    }

    @PostMapping("/redis/getStringValue")
    public BaseReturnVO getStringValue(String key) {
        String value = stringRedisTemplate.opsForValue().get(key);
        Map<String, Object> map = new HashMap<>();
        map.put("key", key);
        map.put("value", value);
        return new BaseReturnVO(map);
    }

    @PostMapping("/redis/list")
    public BaseReturnVO list() {
        /**
         * 字符集合
         */
        List<String> rightList = new ArrayList<>();
        rightList.add("one");
        rightList.add("two");
        rightList.add("three");
        rightList.add("four");

        List<String> leftList = new ArrayList<>();
        leftList.add("first");
        leftList.add("second");

        // 设置list数据
        redisTemplate.opsForList().rightPush("right-list", rightList);
        redisTemplate.opsForList().leftPush("left-list", leftList);

        // 取出list数据
        List<String> rightResult = (List<String>) redisTemplate.opsForList().rightPop("right-list");
        List<String> leftResult = (List<String>) redisTemplate.opsForList().leftPop("left-list");
        System.out.println("rightResult: " + rightResult);
        System.out.println("leftResult: " + leftResult);

        /**
         * 对象集合
         */
        List<ShopAddress> addressList = new ArrayList<>();
        ShopAddress shopAddress = new ShopAddress();
        shopAddress.setId(1);
        shopAddress.setCity("CA");
        shopAddress.setFirstName("Andy");

        ShopAddress shopAddress2 = new ShopAddress();
        shopAddress2.setId(2);
        shopAddress2.setCity("NY");
        shopAddress2.setFirstName("Ribon");
        addressList.add(shopAddress);
        addressList.add(shopAddress2);

        redisTemplate.opsForValue().set("addressList", addressList);

        // 获取对象集合数据
        List<ShopAddress> resultList = (List<ShopAddress>) redisTemplate.opsForValue().get("addressList");
        System.out.println(JSON.toJSON(resultList));

        return new BaseReturnVO(true);
    }

    @PostMapping("/redis/set")
    public BaseReturnVO set() {
        Set<String> set = new HashSet<>();
        set.add("one");
        set.add("two");
        set.add("three");

        // 插入
        redisTemplate.opsForSet().add("set1", set);
        // 获取set数据
        Set resultSet = redisTemplate.opsForSet().members("set1");
        System.out.println("resultSet: " + resultSet);

        return new BaseReturnVO(true);
    }

    @PostMapping("/redis/map")
    public BaseReturnVO map() {
        Map<String, String> map = new HashMap<>();
        map.put("key1", "value1");
        map.put("key2", "value2");
        map.put("key3", "value3");
        map.put("key4", "value4");

        // 插入
        redisTemplate.opsForHash().putAll("map1", map);

        // 获取map里面的值
        Map<String, String> resultMap = redisTemplate.opsForHash().entries("map1");
        System.out.println("resultMap: " + resultMap);

        List<String> resultMapList = redisTemplate.opsForHash().values("map1");
        System.out.println("resultMapList: " + resultMapList);

        // 获取key
        Set<Set> resultMapKey = redisTemplate.opsForHash().keys("map1");
        System.out.println("resultMapKey: " + resultMapKey);

        // 获取value
        String value1 = (String) redisTemplate.opsForHash().get("map1", "key1");
        System.out.println("value1: " + value1);

        return new BaseReturnVO(true);
    }

    /**========================================= redis 操作 ========================================================**/

} 

