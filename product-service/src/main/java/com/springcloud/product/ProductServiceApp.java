package com.springcloud.product;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * @Description:
 * @Author: Sui, ChengBin
 * @Date: 2020/5/15
 **/
@SpringBootApplication
@EnableCircuitBreaker
public class ProductServiceApp {
    public static void main(String[] args) {
        SpringApplication.run(ProductServiceApp.class);
    }
}
