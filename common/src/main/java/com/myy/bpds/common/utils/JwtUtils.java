package com.myy.bpds.common.utils;

import com.myy.bpds.common.dto.UserInfo;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;

/**
 * JWT 工具类
 */
public class JwtUtils {

    // JWT 密钥（生产环境应该从配置文件读取）
    private static final String SECRET_KEY = "bpds-jwt-d2d95562-a382-46e9-a048-67c057b81d0d";

    // Token 有效期：7天（毫秒）
    private static final long TOKEN_EXPIRATION = 700 * 24 * 60 * 60 * 1000L;

    public static String generateToken(UserInfo userInfo) {
        long now = System.currentTimeMillis();
        long expiration = now + TOKEN_EXPIRATION;
        SecretKey key = Keys.hmacShaKeyFor(SECRET_KEY.getBytes(StandardCharsets.UTF_8));
        return Jwts.builder()
                .subject(userInfo.getUserId())
                .claim("username", userInfo.getUsername())
                .issuedAt(new Date(now))
                .expiration(new Date(expiration))
                .signWith(key)
                .compact();
    }

    public static Claims parseToken(String token) {
        SecretKey key = Keys.hmacShaKeyFor(SECRET_KEY.getBytes(StandardCharsets.UTF_8));

        return Jwts.parser()
                .verifyWith(key)
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

    public static UserInfo claimsToUserInfo(Claims claims) {
        UserInfo userInfo = new UserInfo();
        userInfo.setUserId(claims.getSubject());
        userInfo.setUsername(claims.get("username", String.class));
        return userInfo;
    }
}
