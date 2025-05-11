package com.mo.common.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SecureDigestAlgorithm;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;
import java.util.Map;

public class JwtUtil {

    /*
    这些是一组预定义的声明，它们 不是强制性的，而是推荐的 ，以 提供一组有用的、可互操作的声明 。
    iss: jwt签发者
    sub: jwt所面向的用户
    aud: 接收jwt的一方
    exp: jwt的过期时间，这个过期时间必须要大于签发时间
    nbf: 定义在什么时间之前，该jwt都是不可用的.
    iat: jwt的签发时间
    jti: jwt的唯一身份标识，主要用来作为一次性token,从而回避重放攻击
     */


    /**
     * 创建jwt
     * @param secretKey 密钥
     * @param ttl 有效期
     * @param claims claims
     * @return jwt令牌
     */
    public static String createJwt(String secretKey, long ttl, Map<String,Object> claims){
        SecureDigestAlgorithm<SecretKey, SecretKey> alg = Jwts.SIG.HS256;
        Key key = Keys.hmacShaKeyFor(secretKey.getBytes(StandardCharsets.UTF_8));

        long expMills = System.currentTimeMillis() + ttl;
        Date exp = new Date(expMills);

        return Jwts.builder()
                .header()
                .add("typ","JWT")
                .and()
                .claims(claims)
                .expiration(exp)
                .signWith(key)
                .compact();
    }

    /**
     * 解析jwt
     * @param secretKey 密钥
     * @param jwt 令牌
     * @return claims
     */
    public static Jws<Claims> parseJwt(String secretKey, String jwt){
        SecretKey key = Keys.hmacShaKeyFor(secretKey.getBytes(StandardCharsets.UTF_8));
        return Jwts.parser()
                .verifyWith(key)
                .build()
                .parseSignedClaims(jwt);
    }
}
