package com.springcloud.orderservice.service.impl;

import com.fasterxml.jackson.databind.JsonNode;
import com.springcloud.orderservice.domain.ProductOrder;
import com.springcloud.orderservice.service.ProductClient;
import com.springcloud.orderservice.service.ProductOrderService;
import com.springcloud.orderservice.utils.JsonUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
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

    private static Logger logger = LoggerFactory.getLogger(ProductOrderServiceImpl.class);

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

        return getProductOrder(userId, productId, map.get("name"));
    }

    @Override
    public ProductOrder saveWithClient(int userId, int productId) {
        System.out.println("saveWithClient method---");
        ServiceInstance instance = balancerClient.choose("product-service");
        String url = String.format("http://%s:%s/api/v1/product/getProduct?id=%s",
                instance.getHost(), instance.getPort(), productId);
        RestTemplate restTemplate = new RestTemplate();
        Map map = restTemplate.getForObject(url, Map.class);

        return getProductOrder(userId, productId, map.get("name"));
    }

    @Override
    public ProductOrder findWithFeign(int userId, int productId) {
        logger.info("*** findWithFeign Method-----");
        String result = productClient.findById(productId);
        JsonNode jsonNode = JsonUtils.parseToJson(result);
        return getProductOrder(userId, productId, jsonNode.get("name"));
    }

    private ProductOrder getProductOrder(int userId, int productId, Object name) {
        ProductOrder order = new ProductOrder();
        order.setCreateTime(new Date());
        order.setProductId(productId);
        order.setProductName(name.toString());
        order.setUserId(userId);
        order.setTranNo(UUID.randomUUID().toString());

        return order;
    }
}
