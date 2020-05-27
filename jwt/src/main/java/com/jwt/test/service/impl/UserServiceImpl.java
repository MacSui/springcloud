package com.jwt.test.service.impl;

import com.jwt.test.pojo.User;
import com.jwt.test.service.UserService;
import org.springframework.stereotype.Service;

/**
 * @Description:
 * @Author: Sui, ChengBin
 * @Date: 2020/5/27
 **/
@Service
public class UserServiceImpl implements UserService {
    @Override
    public User findUserById(String userId) {
        User user = new User("1", "jwt", "123");
        return user;
    }

    @Override
    public User findUserByName(String userName) {
        User user = new User("1", "jwt", "123");
        return user;
    }
}
