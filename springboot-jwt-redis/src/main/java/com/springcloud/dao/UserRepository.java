package com.springcloud.dao;

import com.springcloud.entiry.User;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

/**
 * @Description:
 * @Author: Sui, ChengBin
 * @Date: 2020/5/27
 **/
@Repository
public class UserRepository {
    private static Map<String, User> userMap = new HashMap<>();

    public User findByUserName(String userName){
        return userMap.get(userName);
    }

    public User save(User user){
        User newUser = new User();
        BeanUtils.copyProperties(user, newUser);
        newUser.setId(1);
        userMap.put(user.getUserName(), newUser);
        return newUser;
    }


}
