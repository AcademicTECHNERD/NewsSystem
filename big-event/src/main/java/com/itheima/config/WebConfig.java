package com.itheima.config;

import com.itheima.interceptors.LoginInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    // 标记该类为 Spring 配置类，用于配置 Web 相关的设置

    @Autowired
    private LoginInterceptor loginInterceptor; // 注入 LoginInterceptor 实例，用于处理登录验证

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 添加拦截器到 Spring MVC 的拦截器注册中心

        // 登录接口和注册接口不进行拦截，允许匿名访问
        registry.addInterceptor(loginInterceptor)
                .excludePathPatterns("/user/login", "/user/register"); // 指定不拦截的路径
    }
}
