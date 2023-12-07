package com.example.example.helper;

import cn.hutool.core.date.DateField;
import cn.hutool.core.date.DateUtil;
import cn.hutool.jwt.JWT;
import cn.hutool.jwt.JWTUtil;

import java.util.Date;
import java.util.UUID;

/**
 * @author fucheng on 2023/12/4
 */
public class JwtHelper {
    private static final String TOKEN_SECRET_KEY = "123456";
    private static final long TOKEN_EXPIRATION = 60 * 60 * 24;
    private static final String PAYLOAD_KEY = "payload";

    // 根据用户名称生成 token
    public static String generateToken(String payloadString) {
        return JWT.create()
                .setPayload(PAYLOAD_KEY, payloadString)
                .setExpiresAt(DateUtil.offset(new Date(), DateField.SECOND, (int) TOKEN_EXPIRATION))
                .setKey(TOKEN_SECRET_KEY.getBytes())
                .sign();
    }

    // 解析并验证token
    public static String parseToken(String token) {
        JWT jwt = JWTUtil.parseToken(token);
        jwt.validate(0);
        return (String) jwt.getPayload(PAYLOAD_KEY);
    }
}
