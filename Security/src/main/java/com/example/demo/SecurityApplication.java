package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 优点：
 * 1.提供了用户认证功能，实现相关接口即可，节约大量开发工作
 * 2.基于Spring，容易集成到Spring项目中
 * <p>
 * 缺点：
 * 1.配置文件多，角色被编码到配置文件和源文件中，RBAC不明显
 * 2.对用户、角色、权限没有可操作界面
 * 3.大数据量情况下几乎不可使用
 *
 * @author asus
 */
@SpringBootApplication
public class SecurityApplication {

    public static void main(String[] args) {
        SpringApplication.run(SecurityApplication.class, args);
    }

}
