package com.jwt.test.service.impl;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.jwt.test.pojo.User;
import com.jwt.test.service.TokenService;
import org.springframework.stereotype.Service;

/**
 * @Description:
 * @Author: Sui, ChengBin
 * @Date: 2020/5/27
 **/
@Service
public class TokenServiceImpl implements TokenService {
    @Override
    public String getToken(User user) {
        String token = "";
        token = JWT.create().withAudience(user.getId()).sign(Algorithm.HMAC256(user.getPassword()));
        return token;
    }
}
