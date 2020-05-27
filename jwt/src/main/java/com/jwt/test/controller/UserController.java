package com.jwt.test.controller;

import com.alibaba.fastjson.JSONObject;
import com.jwt.test.pojo.User;
import com.jwt.test.service.TokenService;
import com.jwt.test.service.UserService;
import com.jwt.test.token.UserLoginToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @Description:
 * @Author: Sui, ChengBin
 * @Date: 2020/5/27
 **/
@RestController
@RequestMapping("/api")
public class UserController {
    @Autowired
    UserService userService;
    @Autowired
    TokenService tokenService;

    @GetMapping("/login1")
    public Object login1(){
        return "login1";
    }

    @PostMapping("/login")
    public Object login(@RequestBody User user){
        JSONObject jsonObject = new JSONObject();
        System.out.println("controller login method");
        User userForBase = userService.findUserByName(user.getUserName());
        System.out.println("userForBase:"+userForBase);
        if (userForBase == null){
            jsonObject.put("message","登录失败，用户不存在");
            return jsonObject;
        }else{
            if (!userForBase.getPassword().equals(user.getPassword())){
                jsonObject.put("message","登录失败，密码不正确");
                return jsonObject;
            }else {
                String token = tokenService.getToken(userForBase);
                jsonObject.put("token", token);
                jsonObject.put("user", userForBase);
                return jsonObject;
            }
        }
    }

    @UserLoginToken
    @GetMapping("/getMessage")
    public String getMessage(){
        return "验证通过";
    }

}
