package com.itheima.config;

import org.springframework.cache.annotation.CachingConfigurerSupport; // 引入缓存支持类
import org.springframework.context.annotation.Bean; // 引入Bean注解
import org.springframework.context.annotation.Configuration; // 引入配置注解
import org.springframework.data.redis.connection.RedisConnectionFactory; // 引入Redis连接工厂
import org.springframework.data.redis.core.RedisTemplate; // 引入通用Redis模板
import org.springframework.data.redis.core.StringRedisTemplate; // 引入字符串Redis模板

@Configuration // 声明这是一个配置类
public class RedisConfig extends CachingConfigurerSupport { // 继承缓存支持类

    // 定义一个StringRedisTemplate Bean，用于操作String类型的键值对
    @Bean
    public StringRedisTemplate stringRedisTemplate(RedisConnectionFactory redisConnectionFactory) {
        // 使用提供的连接工厂创建StringRedisTemplate实例
        return new StringRedisTemplate(redisConnectionFactory);
    }

    // 定义一个通用的RedisTemplate Bean，用于操作任意类型的对象
    @Bean
    public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory redisConnectionFactory) {
        // 创建RedisTemplate实例
        RedisTemplate<String, Object> template = new RedisTemplate<>();
        // 设置连接工厂，以便连接到Redis服务器
        template.setConnectionFactory(redisConnectionFactory);
        // 返回配置好的RedisTemplate实例
        return template;
    }
}
