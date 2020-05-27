package com.springcloud.product.service.impl;

import com.springcloud.product.domain.Product;
import com.springcloud.product.service.ProductService;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * @Description:
 * @Author: Sui, ChengBin
 * @Date: 2020/5/15
 **/
@Service
public class ProductServiceImpl implements ProductService {

    private static final Map<Integer, Product> daoMap = new HashMap<>();
    static{
        Product p1 = new Product(1, "iphone1", 1999, 10);
        Product p2 = new Product(2, "iphone2", 2999, 20);
        Product p3 = new Product(3, "iphone3", 3999, 30);
        Product p4 = new Product(4, "iphone4", 4999, 40);
        Product p5 = new Product(5, "iphone5", 5999, 50);
        Product p6 = new Product(6, "iphone6", 6999, 60);
        daoMap.put(1, p1);
        daoMap.put(2, p2);
        daoMap.put(3, p3);
        daoMap.put(4, p4);
        daoMap.put(5, p5);
        daoMap.put(6, p6);

    }

    @Override
    public List<Product> listProduct() {
        Collection<Product> collection = daoMap.values();
        List<Product> products = new ArrayList<>(collection);

        return products;
    }

    @Override
    public Product findByid(int id) {
        return daoMap.get(id);
    }
}
