package com.lockie.user.service;

import com.lockie.common.util.Md5Util;
import com.lockie.user.config.Constants;
import com.lockie.user.mapper.UserMapper;
import com.lockie.user.model.User;
import com.lockie.user.model.UserExample;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    public User getUserByName(String userName) {
        if (StringUtils.isEmpty(userName)) {
            return null;
        }
        UserExample example = new UserExample();
        example.createCriteria().andUserNameEqualTo(userName);
        List<User> userList = userMapper.selectByExample(example);
        if (null != userList && userList.size() > 0) {
            return userList.get(0);
        }
        return null;
    }

    /**
     * 用户保存和更新
     * @param user
     * @return
     * @throws Exception
     */
    public User saveAndUpdateUser(User user) throws Exception {
        if (null == user) {
            return null;
        }
        /**
         * 新增
         */
        if (null == user.getId()) {
            // 密码加密
            if (StringUtils.isNotEmpty(user.getPassword())) {
                // 获取加密后的密码
                String md5Password = Md5Util.getMD5(user.getPassword());
                if (StringUtils.isNotEmpty(md5Password)) {
                    user.setPassword(md5Password);
                }
            }
            user.setStatus(Constants.UserStatus.ACTIVE);
            user.setCreateDate(new Date());
            userMapper.insert(user);
        } else {
            /**
             * 更新
             */
            user.setUpdateDate(new Date());
            userMapper.updateByPrimaryKeySelective(user);
        }
        return user;
    }

    /**
     * 校验用户名和密码
     * @param user
     * @return
     */
    public Map<Boolean, String> checkLogin(User user) {
        Map<Boolean, String> map = new HashMap<>();
        if (StringUtils.isEmpty(user.getUserName()) || StringUtils.isEmpty(user.getPassword())) {
            map.put(false, "用户名或者密码为空！");
            return map;
        }
        if (null != user.getStatus() && Constants.UserStatus.NO_ACTIVE.equals(user.getStatus())) {
            map.put(false, "用户被禁止！");
            return map;
        }
        // 获取加密后的密码
        String md5Password = Md5Util.getMD5(user.getPassword());
        UserExample example = new UserExample();
        example.createCriteria().andUserNameEqualTo(user.getUserName()).andPasswordEqualTo(md5Password);
        List<User> list = userMapper.selectByExample(example);
        if (null != list && list.size() > 0) {
            map.put(true, "success");
            return map;
        } else {
            map.put(false, "用户名或者密码不对！");
            return map;
        }
    }

    /**
     * 根据用户名和密码查询
     * @param userName
     * @param password
     * @return
     */
    public User getUserByUserNameAndPwd(String userName, String password) {
        if (StringUtils.isEmpty(userName) || StringUtils.isEmpty(password)) {
            return null;
        }
        // 获取加密后的密码
        String md5Password = Md5Util.getMD5(password);
        UserExample example = new UserExample();
        example.createCriteria().andUserNameEqualTo(userName).andPasswordEqualTo(md5Password);
        List<User> list = userMapper.selectByExample(example);
        if (null != list && list.size() > 0) {
            return list.get(0);
        } else {
            return null;
        }
    }
} 

