package com.springcloud.module;

import lombok.Data;

/**
 * @Description:
 * @Author: Sui, ChengBin
 * @Date: 2020/5/27
 **/
@Data
public class LoginUser {
    private String userName;
    private String password;
    private Integer rememberMe;
}
