package com.sunvalley.user.service;

import com.sunvalley.user.mapper.UserMapper;
import com.sunvalley.user.model.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 类或方法的功能描述 : 用户接口服务
 *
 * @author: logan.zou
 * @date: 2018-08-10 12:03
 */
@Slf4j
@Service
public class UserService {
    @Autowired
    UserMapper userMapper;

    /**
     * 根据用户ID查询
     * @param id
     * @return
     */
    public User getUserById(Integer id) {
        return userMapper.selectByPrimaryKey(id);
    }
} 

