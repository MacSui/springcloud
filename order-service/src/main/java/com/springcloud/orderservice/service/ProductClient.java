package com.springcloud.orderservice.service;

import com.springcloud.orderservice.fallback.ProductClientFallback;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @Description:
 * @Author: Sui, ChengBin
 * @Date: 2020/5/19
 **/
@FeignClient(value = "product-service", fallback = ProductClientFallback.class)
public interface ProductClient {

    @GetMapping("/api/v1/product/getProduct")
    String findById(@RequestParam("id") int id);

}
