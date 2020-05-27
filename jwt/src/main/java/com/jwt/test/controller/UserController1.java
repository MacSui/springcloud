package com.jwt.test.controller;

import com.jwt.test.pojo.UserLombok;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description:
 * @Author: Sui, ChengBin
 * @Date: 2020/5/27
 **/
@RestController
@RequestMapping("/api/v1")
public class UserController1 {
    @PostMapping("/login")
    public Object login(@RequestBody UserLombok user){
        System.out.println("user:"+user);
        return null;
    }
}
