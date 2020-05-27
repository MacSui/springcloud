package com.jwt.test.Interceptor;

import com.alibaba.fastjson.JSONObject;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Description:
 * @Author: Sui, ChengBin
 * @Date: 2020/5/27
 **/
@ControllerAdvice
public class GloableExceptionHandler {
    @ResponseBody
    @ExceptionHandler(Exception.class)
    public Object handleException(Exception e){
        String msg = e.getMessage();
        if (msg==null || msg.equals("")){
            msg = "服务器出错";
        }
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("message",msg);
        return jsonObject;
    }
}
