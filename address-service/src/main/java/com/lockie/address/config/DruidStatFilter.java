package com.lockie.address.config;

import com.alibaba.druid.support.http.WebStatFilter;

import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;

/**
 * 类或方法的功能描述 : druid过滤器
 *
 * @author: logan.zou
 * @date: 2018-07-17 11:59
 */
@WebFilter(
        filterName = "druidWebStatFilter", urlPatterns = "/*",
        initParams = {
                @WebInitParam(name = "exclusions", value = "*.js,*.gif,*.jpg,*.bmp,*.png,*.css,*.ico,/druid/*") // 忽略资源
        }
)
public class DruidStatFilter extends WebStatFilter {

} 

