package com.jwt.test.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * @Description:
 * @Author: Sui, ChengBin
 * @Date: 2020/5/27
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    String id;
    String userName;
    String password;

}
