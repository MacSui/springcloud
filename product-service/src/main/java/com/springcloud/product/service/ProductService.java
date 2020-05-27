package com.springcloud.product.service;

import com.springcloud.product.domain.Product;

import java.util.List;

/**
 * @Description:
 * @Author: Sui, ChengBin
 * @Date: 2020/5/15
 **/
public interface ProductService {

    List<Product> listProduct();

    Product findByid(int id);

}
