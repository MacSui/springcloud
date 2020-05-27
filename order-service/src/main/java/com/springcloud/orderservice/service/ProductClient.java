package com.springcloud.orderservice.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @Description:
 * @Author: Sui, ChengBin
 * @Date: 2020/5/19
 **/
@FeignClient("product-service")
public interface ProductClient {

    @GetMapping("/api/v1/product/getProduct")
    String findById(@RequestParam("id") int id);

}
