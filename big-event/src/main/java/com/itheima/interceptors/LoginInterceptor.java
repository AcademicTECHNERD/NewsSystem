package com.itheima.interceptors;

import com.itheima.utils.JwtUtil;
import com.itheima.utils.ThreadLocalUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

@Component
public class LoginInterceptor implements HandlerInterceptor {
    @Autowired
    private StringRedisTemplate stringRedisTemplate; // 注入 Redis 模板，用于操作 Redis 数据库
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // 令牌验证
        String token = request.getHeader("Authorization"); // 从请求头中获取名为 "Authorization" 的令牌
        // 验证 token
        try {
            // 从 Redis 中获取与请求中相同的 token
            ValueOperations<String, String> operations = stringRedisTemplate.opsForValue();
            String redisToken = operations.get(token); // 查询 Redis 数据库
            if (redisToken == null) {
                // 如果 Redis 中没有该 token，说明 token 已经失效
                throw new RuntimeException("Token has expired"); // 抛出异常以指示 token 失效
            }
            // 解析 token，获取业务数据（例如用户信息）
            Map<String, Object> claims = JwtUtil.parseToken(token);
            // 将业务数据存储到 ThreadLocal 中，以便后续处理使用
            ThreadLocalUtil.set(claims);
            // 放行请求，继续处理
            return true;
        } catch (Exception e) {
            // 如果发生异常（例如 token 失效），设置 HTTP 响应状态码为 401（未授权）
            response.setStatus(401);
            // 拦截请求，不放行
            return false;
        }
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        // 清空 ThreadLocal 中的数据，以释放内存和避免数据污染
        ThreadLocalUtil.remove();
    }
}

