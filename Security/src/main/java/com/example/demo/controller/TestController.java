package com.example.demo.controller;

import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PostFilter;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.access.prepost.PreFilter;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * 控制层
 *
 * @author asus
 */
@RestController
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class TestController {

    @GetMapping("/")
    public String home() {
        return "home page";
    }

    @GetMapping("/msg")
    public String getMsg() {
        return "hello world";
    }

    /**
     * 方法调用前进行权限检查
     *
     * @return PreAuthorize
     * @PreAuthorize 权限校验，拥有相应权限才能访问，使用本注解的类必须增加 @EnableGlobalMethodSecurity(prePostEnabled = true)
     * hasRole中的权限必须以 ROLE_ 开头，然后后面跟 SpringSecurityConfig类 configure方法中的设置的账号密码权限
     * hasRole中可以使用 and or 表达式
     */
    @PreAuthorize("hasRole('ROLE_admin')")
    @GetMapping("/preAuthorize")
    public String preAuthorize() {
        return "PreAuthorize";
    }

    /**
     * 方法调用后进行权限检查
     *
     * @return PostAuthorize
     */
    @PostAuthorize("hasRole('ROLE_user')")
    @GetMapping("/postAuthorize")
    public String postAuthorize() {
        return "PostAuthorize";
    }

    @PreAuthorize("#id<10 and principal.username.equals(#userName)")
    @GetMapping("/test")
    public String test(Integer id, String userName) {
        return "test";
    }

    @PreFilter("hasRole('ROLE_admin')")
    @GetMapping("/preFilter")
    public List preFilter() {
        return new ArrayList();
    }

    @PostFilter("hasRole('ROLE_admin')")
    @GetMapping("/postFilter")
    public List postFilter() {
        return new ArrayList();
    }

}