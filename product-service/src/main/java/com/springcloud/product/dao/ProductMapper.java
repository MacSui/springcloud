package com.springcloud.product.dao;

import com.springcloud.product.domain.Product;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @Description:
 * @Author: Sui, ChengBin
 * @Date: 2020/7/9
 **/
@Mapper
public interface ProductMapper {

    public List<Product> listProducts();

    public Product findByid(int id);

}
