package com.lockie.product.controller;

import com.lockie.common.vo.BaseReturnVO;
import com.lockie.product.model.ShopProduct;
import com.lockie.product.service.ShopProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 类或方法的功能描述 : 产品接口
 *
 * @author: logan.zou
 * @date: 2018-06-22 16:18
 */
@Slf4j
@RestController
@RequestMapping("/product")
public class ProductController {
    @Value("${server.port}")
    private String port;
    @Autowired
    ShopProductService shopProductService;

    /**
     * 获取服务端口号
     * @return
     */
    @GetMapping("/getPort")
    public String getPort() {
        log.info("product-service port：" + port);
        return "product-service port：" + port;
    }

    /**
     * 根据ID查询
     * @param id
     * @return
     */
    @GetMapping("/getShopProductById/{id}")
    public BaseReturnVO getShopProductById(@PathVariable("id") Integer id) {
        ShopProduct product = shopProductService.getShopProductById(id);
        return new BaseReturnVO(product);
    }
} 

