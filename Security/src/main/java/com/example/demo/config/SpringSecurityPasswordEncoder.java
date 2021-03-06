package com.example.demo.config;

import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * 密码自定义验证
 * 1.实现 PasswordEncoder 接口
 * 2.重写 encode matches方法
 *
 * @author asus
 */
public class SpringSecurityPasswordEncoder implements PasswordEncoder {

    /**
     * 加密密码
     *
     * @param charSequence 输入的密码
     * @return 加密的密码
     */
    @Override
    public String encode(CharSequence charSequence) {
        return charSequence.toString();
    }

    /**
     * 匹配密码
     *
     * @param charSequence 输入的密码
     * @param s            存储的已编码密码
     * @return 密码是否匹配
     */
    @Override
    public boolean matches(CharSequence charSequence, String s) {
        return s.equals(charSequence.toString());
    }
}
