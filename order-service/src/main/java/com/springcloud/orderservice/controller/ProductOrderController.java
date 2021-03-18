package com.springcloud.orderservice.controller;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.springcloud.orderservice.domain.ProductOrder;
import com.springcloud.orderservice.service.ProductOrderService;
import com.springcloud.orderservice.utils.JsonUtils;
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
import java.util.HashMap;
import java.util.Map;

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
    @GetMapping("findWithFeign")
    @HystrixCommand(fallbackMethod = "findWithFeignFallback")
    public Object findWithFeign(@RequestParam("user_id")int userId, @RequestParam("product_id")int productId){
        ProductOrder order = orderService.findWithFeign(userId, productId);
        Map<String, Object> data = new HashMap<>();
        data.put("code", "1");
        data.put("data", order);
        return data;
    }

    public Object findWithFeignFallback(int userId, int productId){
        Map<String, Object> map = new HashMap<>();
        map.put("code", "-1");
        map.put("msg", "当前人数太多，请稍后再试！");
        return map;
    }

}
