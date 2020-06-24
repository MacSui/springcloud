package com.springcloud.orderservice.controller;

import com.springcloud.orderservice.domain.ProductOrder;
import com.springcloud.orderservice.service.ProductOrderService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * @Description:
 * @Author: Sui, ChengBin
 * @Date: 2020/5/19
 **/
@Api("订单rest controller")
@RestController
@RequestMapping("/api/v1/order")
public class ProductOrderController {

    @Autowired
    private ProductOrderService orderService;

    @ApiOperation("订单保存")
    @ApiImplicitParams({@ApiImplicitParam(name="user_id", value = "用户id", paramType = "query", dataType = "int"),
    @ApiImplicitParam(name = "product_id", value = "产品Id", paramType = "query", dataType = "int")})
    @GetMapping("save")
    public ProductOrder save(@RequestParam("user_id") int userId, @RequestParam("product_id") int productId){

        return orderService.save(userId, productId);
    }

    @GetMapping("saveWithClient")
    public ProductOrder saveWithClient(@RequestParam("user_id") int userId, @RequestParam("product_id") int productId, HttpServletRequest request){
        String s = null;
        s.equals("a");
        String token = request.getHeader("token");
        String cookie = request.getHeader("cookie");
        System.out.println("order-service saveWithClient, token="+token+", cookie="+cookie);

        return orderService.saveWithClient(userId, productId);
    }
    @GetMapping("saveWithFeign")
    public ProductOrder saveWithFeign(@RequestParam("user_id")int userId, @RequestParam("product_id")int productId){
        return orderService.saveWithFeign(userId, productId);
    }


}
