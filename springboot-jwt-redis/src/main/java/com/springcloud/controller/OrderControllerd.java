package com.springcloud.controller;

import org.springframework.web.bind.annotation.*;

/**
 * @Description:
 * @Author: Sui, ChengBin
 * @Date: 2020/5/27
 **/
@RestController
@RequestMapping("/order")
public class OrderControllerd {
    @GetMapping("/list")
    public String listOrders(){
        return "order list";
    }

    @PostMapping("/create")
    public String createOrder(){
        return "new order";
    }

    @PutMapping("/update/{orderId}")
    public String updateOrder(@PathVariable Integer orderId){
        return "update order which id="+orderId;
    }

    @DeleteMapping("/delete/{orderId}")
    public String deleteOrder(@PathVariable Integer orderId){
        return "delete order which id="+orderId;
    }
}
