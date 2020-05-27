package com.springcloud.orderservice.utils;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

/**
 * @Description:
 * @Author: Sui, ChengBin
 * @Date: 2020/5/19
 **/
public class JsonUtils {

    private static final ObjectMapper objectMapper = new ObjectMapper();

    public static JsonNode parseToJson(String str){
        try {
            return objectMapper.readTree(str);
        } catch (IOException e) {
            e.printStackTrace();
            return  null;
        }
    }

}
