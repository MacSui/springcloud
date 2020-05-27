package com.springcloud.product.controller;

import com.springcloud.product.domain.Product;
import com.springcloud.product.service.ProductService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Description:
 * @Author: Sui, ChengBin
 * @Date: 2020/5/15
 **/
@RestController
@RequestMapping("/api/v1/product")
public class ProductController {

    @Value("${server.port}")
    private String port;

    @Autowired
    private ProductService productService;

    @GetMapping("/getProduct")
    public Product getProductById(@RequestParam("id") int id){
        Product product = productService.findByid(id);
        Product result = new Product();
        BeanUtils.copyProperties(product, result);
        result.setName(result.getName()+"-port:"+port);
        return result;
    }

    @RequestMapping("/showAllProducts")
    public List getAllProducts(){
        return productService.listProduct();
    }
}
