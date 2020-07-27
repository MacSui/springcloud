package com.springcloud.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;

/**
 * @Description:
 * @Author: Sui, ChengBin
 * @Date: 2020/5/27
 **/
public class JwtTokenUtils {

    public static final String TOKEN_HEADER = "Authorization";
    public static final String TOKEN_PREFIX = "Bearer";

    private static final String SECRET = "jwtsecretdemo";
    private static final String ISS = "echisan";

    //过期时间3600s，1小时
    private static final long EXPIRATION = 3600L;

    private static final long EXPIRATION_MEMEBER = 604800L; //记住登录状态，7天

    /**
     * 创建token
     * @param userName
     * @param isRememberMe
     * @return
     */
    public static String createToken(String userName, boolean isRememberMe){
        long expiration = isRememberMe?EXPIRATION_MEMEBER:EXPIRATION;
        return Jwts.builder()
                .signWith(SignatureAlgorithm.HS512, SECRET)
                .setIssuer(ISS)
                .setSubject(userName)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + expiration * 1000))
                .compact();
    }

    /**
     * 从token中获取uerName
     * @param token
     * @return
     */
    public static String getUserName(String token){
        return getTokenBody(token).getSubject();
    }

    /**
     * 判断token是否过期
     * @param token
     * @return
     */
    public static boolean isExpiration(String token){
        return getTokenBody(token).getExpiration().before(new Date());
    }

    private static Claims getTokenBody(String token) {
        return Jwts.parser()
                .setSigningKey(SECRET)
                .parseClaimsJws(token)
                .getBody();
    }

}
