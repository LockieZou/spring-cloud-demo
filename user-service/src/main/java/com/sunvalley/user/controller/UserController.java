package com.sunvalley.user.controller;

import com.sunvalley.common.vo.BaseReturnVO;
import com.sunvalley.user.model.User;
import com.sunvalley.user.remote.OrderRemote;
import com.sunvalley.user.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 类或方法的功能描述 : 用户接口
 *
 * @author: logan.zou
 * @date: 2018-06-21 11:46
 */
@Slf4j
@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    OrderRemote orderRemote;
    @Value("${server.port}")
    String port;
    @Autowired
    UserService userService;

    /**
     * 获取用户服务的端口
     * @return
     */
    @GetMapping("/getPort")
    public String getPort() {
        log.info("user-service port：" + port);
        return "user-service port：" + port;
    }

    /**
     * 获取订单服务的端口
     * @return
     */
    @GetMapping("/getOrderPort")
    public String getOrderPort() {
        log.info("user-order-service port：" + orderRemote.getPort());
        return "user-order-service port：" + orderRemote.getPort();
    }

    /**
     * 根据用户ID查询
     * @param id
     * @return
     */
    @GetMapping("/getUserById/{id}")
    public BaseReturnVO getUserById(@PathVariable("id") Integer id) {
        User user = userService.getUserById(id);
        return new BaseReturnVO(user);
    }
} 

