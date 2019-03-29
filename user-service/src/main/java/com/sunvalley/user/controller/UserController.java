package com.sunvalley.user.controller;

import com.sunvalley.common.enums.ApiMsgEnum;
import com.sunvalley.common.vo.BaseReturnVO;
import com.sunvalley.user.model.User;
import com.sunvalley.user.remote.OrderRemote;
import com.sunvalley.user.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

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

    /**
     * 根据用户名查询
     * @param userName
     * @return
     */
    @PostMapping("/getUserByName")
    public BaseReturnVO getUserByName(String userName) {
        return new BaseReturnVO(userService.getUserByName(userName));
    }

    /**
     * 保存用户
     * @param user
     * @return
     */
    @PostMapping("/saveUser")
    public BaseReturnVO saveUser(@RequestBody User user) {
        if (null == user) {
            return new BaseReturnVO(ApiMsgEnum.OK.getResCode(), "用户信息为空");
        }
        try {
            User userTmp = userService.saveAndUpdateUser(user);
            return new BaseReturnVO(userTmp);
        } catch (Exception e) {
            log.error("保存用户失败");
            return new BaseReturnVO(ApiMsgEnum.INTERNAL_SERVER_ERROR.getResCode(), "保存用户信息失败", e);
        }
    }

    /**
     * 测试AOP切面接口
     * @param exception
     * @return
     */
    @PostMapping("/list/{exception}")
    public List<User> testAspect(@PathVariable("exception") Boolean exception) {
        if (exception) {
            throw new Error("测试抛出异常");
        }
        User user = userService.getUserById(1);
        List<User> list = new ArrayList<>();
        list.add(user);
        return list;
    }
} 

