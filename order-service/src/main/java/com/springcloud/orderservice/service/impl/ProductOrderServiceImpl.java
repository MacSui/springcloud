package com.springcloud.orderservice.service.impl;

import com.fasterxml.jackson.databind.JsonNode;
import com.springcloud.orderservice.domain.ProductOrder;
import com.springcloud.orderservice.service.ProductClient;
import com.springcloud.orderservice.service.ProductOrderService;
import com.springcloud.orderservice.utils.JsonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Date;
import java.util.Map;
import java.util.UUID;

/**
 * @Description:
 * @Author: Sui, ChengBin
 * @Date: 2020/5/18
 **/
@Service
public class ProductOrderServiceImpl implements ProductOrderService {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private LoadBalancerClient balancerClient;

    @Autowired
    private ProductClient productClient;

    @Override
    public ProductOrder save(int userId, int productId) {
        System.out.println("save with restTemplate method----");
        Map<String, Object> map = restTemplate.getForObject("http://product-service/api/v1/product/getProduct?id=" + productId,
                Map.class);

        ProductOrder order = new ProductOrder();
        order.setCreateTime(new Date());
        order.setProductId(productId);
        order.setProductName(map.get("name").toString());
        order.setUserId(userId);
        order.setTranNo(UUID.randomUUID().toString());

        return order;
    }

    @Override
    public ProductOrder saveWithClient(int userId, int productId) {
        System.out.println("saveWithClient method---");
        ServiceInstance instance = balancerClient.choose("product-service");
        String url = String.format("http://%s:%s/api/v1/product/getProduct?id=%s",
                instance.getHost(), instance.getPort(), productId);
        RestTemplate restTemplate = new RestTemplate();
        Map map = restTemplate.getForObject(url, Map.class);

        ProductOrder order = new ProductOrder();
        order.setCreateTime(new Date());
        order.setProductId(productId);
        order.setProductName(map.get("name").toString());
        order.setUserId(userId);
        order.setTranNo(UUID.randomUUID().toString());

        return order;
    }

    @Override
    public ProductOrder saveWithFeign(int userId, int productId) {
        System.out.println("saveWithFeign Method-----");
        String result = productClient.findById(productId);
        JsonNode jsonNode = JsonUtils.parseToJson(result);
        ProductOrder order = new ProductOrder();
        order.setCreateTime(new Date());
        order.setProductId(productId);
        order.setProductName(jsonNode.get("name").toString());
        order.setUserId(userId);
        order.setTranNo(UUID.randomUUID().toString());

        return order;
    }
}
