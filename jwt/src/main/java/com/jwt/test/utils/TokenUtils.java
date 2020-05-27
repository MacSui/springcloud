package com.jwt.test.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.jwt.test.pojo.User;

/**
 * @Description:
 * @Author: Sui, ChengBin
 * @Date: 2020/5/27
 **/
public class TokenUtils {
    public static String getToken(User user){
        String token = "";
        token = JWT.create().withAudience(user.getId()).sign(Algorithm.HMAC256(user.getPassword()));
        return token;
    }

}
