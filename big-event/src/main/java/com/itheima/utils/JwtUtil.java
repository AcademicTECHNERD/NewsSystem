package com.itheima.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;

import java.util.Date;
import java.util.Map;

public class JwtUtil {

    private static final String KEY = "itheima";
	
	//接收业务数据,生成token并返回
    public static String genToken(Map<String, Object> claims) {
        return JWT.create()
                .withClaim("claims", claims)   //添加载荷claims
                .withExpiresAt(new Date(System.currentTimeMillis() + 1000 * 60 * 60 ))   //添加过期时间
                .sign(Algorithm.HMAC256(KEY));       //加密方式
    }

	//接收token,验证token,并返回业务数据
    public static Map<String, Object> parseToken(String token) {
        return JWT.require(Algorithm.HMAC256(KEY))  //JWT.require作为一个JWT的验证器
                .build()  //构建 JWT 验证器的实例
                .verify(token)  //使用验证器验证传入的 token。如果 token 无效（如签名不匹配或已过期），会抛出异常。
                .getClaim("claims") //从验证后的 token 中提取名为 "claims" 的声明（claims）。声明是存储在 JWT 中的信息。
                .asMap();  //将提取的 claims 转换为 Map<String, Object>，使得业务数据可以以键值对的形式访问。
    }

}
