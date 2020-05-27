package com.springcloud.orderservice.service;

import com.springcloud.orderservice.domain.ProductOrder;

/**
 * @Description:
 * @Author: Sui, ChengBin
 * @Date: 2020/5/18
 **/
public interface ProductOrderService {

    ProductOrder save(int userId, int productId);

    ProductOrder saveWithClient(int userId, int productId);

    ProductOrder saveWithFeign(int userId, int productId);

}
