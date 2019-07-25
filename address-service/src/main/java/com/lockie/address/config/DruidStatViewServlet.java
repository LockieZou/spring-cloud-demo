package com.lockie.address.config;

import com.alibaba.druid.support.http.StatViewServlet;

import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;

/**
 * 类或方法的功能描述 : Druid控制配置
 *
 * @author: logan.zou
 * @date: 2018-07-17 11:54
 */
@WebServlet(
        urlPatterns = "/druid/*",
        initParams = {
                @WebInitParam(name = "allow", value = "127.0.0.1"), // IP白名单(没有配置或者为空，则允许所有访问
                @WebInitParam(name = "deny", value = ""), // IP黑名单 (存在共同时，deny优先于allow)
                @WebInitParam(name = "loginUserName", value = "admin"), // 用户名
                @WebInitParam(name = "loginPassword", value = "123456"), // 密码
                @WebInitParam(name = "resetEnable", value = "false") // 禁用HTML页面上的"Reset All" 功能
        }
)
public class DruidStatViewServlet extends StatViewServlet {

} 

