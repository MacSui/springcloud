package com.jwt.test.service;

import com.jwt.test.pojo.User;

/**
 * @Description:
 * @Author: Sui, ChengBin
 * @Date: 2020/5/27
 **/
public interface UserService {
    User findUserById(String userId);
    User findUserByName(String userName);
}
