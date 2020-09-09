package com.lockie.address.config;

import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.util.DigestUtils;

/**
 * @author: lockie
 * @Date: 2020/6/2 14:43
 * @Description: 加密解密配置配
 */
@Component
public class MyPasswordEncoder implements PasswordEncoder {
    /**
     * 加密
     * @param charSequence
     * @return
     */
    @Override
    public String encode(CharSequence charSequence) {
        return DigestUtils.md5DigestAsHex(charSequence.toString().getBytes());
    }

    /**
     * 判断是否匹配
     * @param charSequence
     * @return
     */
    @Override
    public boolean matches(CharSequence charSequence, String password) {
        return charSequence.equals(DigestUtils.md5DigestAsHex(password.toString().getBytes()));
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(10);
    }
}
