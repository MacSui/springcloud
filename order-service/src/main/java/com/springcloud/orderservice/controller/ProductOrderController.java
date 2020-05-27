package com.springcloud.orderservice.controller;

import com.springcloud.orderservice.domain.ProductOrder;
import com.springcloud.orderservice.service.ProductOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description:
 * @Author: Sui, ChengBin
 * @Date: 2020/5/19
 **/
@RestController
@RequestMapping("/api/v1/order")
public class ProductOrderController {

    @Autowired
    private ProductOrderService orderService;

    @GetMapping("save")
    public ProductOrder save(@RequestParam("user_id") int userId, @RequestParam("product_id") int productId){

        return orderService.save(userId, productId);
    }

    @GetMapping("saveWithClient")
    public ProductOrder saveWithClient(@RequestParam("user_id") int userId, @RequestParam("product_id") int productId){

        return orderService.saveWithClient(userId, productId);
    }
    @GetMapping("saveWithFeign")
    public ProductOrder saveWithFeign(@RequestParam("user_id")int userId, @RequestParam("product_id")int productId){
        return orderService.saveWithFeign(userId, productId);
    }


}
