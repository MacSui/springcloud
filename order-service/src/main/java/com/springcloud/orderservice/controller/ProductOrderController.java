package com.springcloud.orderservice.controller;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.springcloud.orderservice.domain.ProductOrder;
import com.springcloud.orderservice.service.ProductOrderService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @Description:
 * @Author: Sui, ChengBin
 * @Date: 2020/5/19
 **/
@Api("订单rest controller")
@RestController
@RequestMapping("/api/v1/order")
public class ProductOrderController {

    private static Logger logger = LoggerFactory.getLogger(ProductOrderController.class);

    @Autowired
    private ProductOrderService orderService;

    @Autowired
    private StringRedisTemplate redisTemplate;

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
    @HystrixCommand(fallbackMethod = "saveWithFeignFallback")
    public Object saveWithFeign(@RequestParam("user_id")int userId, @RequestParam("product_id")int productId){
        ProductOrder order = orderService.saveWithFeign(userId, productId);
        Map<String, Object> data = new HashMap<>();
        data.put("code", "1");
        data.put("data", order);
        return data;
    }

    public Object saveWithFeignFallback(int userId, int productId){

        new Thread(()->{
            String saveKey = "save-order-key";
            String saveKeyValue = redisTemplate.opsForValue().get(saveKey);
            if (StringUtils.isBlank(saveKeyValue)){
                logger.info("失败告警：下单失败，请查找失败原因！");
                redisTemplate.opsForValue().set(saveKey, "save order fail", 30, TimeUnit.SECONDS);
            } else {
                logger.info("短信已发送，30s内不能重复发送");
            }
        }).start();

        Map<String, Object> map = new HashMap<>();
        map.put("code", "-1");
        map.put("msg", "当前人数太多，请稍后再试！");
        return map;
    }

}
