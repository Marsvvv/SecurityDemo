package com.example.demo.config;

import com.example.demo.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * Spring Security 配置类
 *
 * @author asus
 */
@Configuration
@EnableWebSecurity
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserServiceImpl userService;

    /**
     * 设置静态文件目录，防止静态目录被拦截器拦截
     *
     * @param web
     * @throws Exception
     */
    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/js/**", "/css/**");
    }

    /**
     * HTTP请求的拦截
     * 决定哪些请求会被拦截
     *
     * @param http
     * @throws Exception
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                //  项目主路径放行
                .antMatchers("/").permitAll()
                //  其余路径全部需要身份验证
                .anyRequest().authenticated()
                .and()
                //  允许注销
                .logout().permitAll()
                .and()
                //  允许Form表单登录
                .formLogin();
        //  关闭CSRF策略
        http.csrf().disable();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth.inMemoryAuthentication()
//                .withUser("user")
//                //  {noop} 后增加密码才能使用 TODO 未知原因
//                .password("{noop}123456")
//                .roles("user");
//        auth.inMemoryAuthentication()
//                .withUser("admin")
//                //  {noop} 后增加密码才能使用 TODO 未知原因
//                .password("{noop}123456")
//                .roles("admin");
//        auth.inMemoryAuthentication()
//                .withUser("Garvey")
//                //  {noop} 后增加密码才能使用 TODO 未知原因
//                .password("{noop}123456")
//                .roles("admin");

        //  配置 数据库密码查询服务类 和 密码验证器
        auth.userDetailsService(userService).passwordEncoder(new SpringSecurityPasswordEncoder());

        //  指定数据库验证
        auth.jdbcAuthentication()
                //  指定查询user的方法
                .usersByUsernameQuery("")
                //  根据权限查询
                .authoritiesByUsernameQuery("")
                //  密码认证
                .passwordEncoder(new SpringSecurityPasswordEncoder());
    }
}
