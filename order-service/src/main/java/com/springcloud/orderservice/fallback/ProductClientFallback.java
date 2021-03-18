package com.springcloud.orderservice.fallback;

import com.springcloud.orderservice.service.ProductClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * @Description:
 * @Author: Sui, ChengBin
 * @Date: 2021/3/18
 **/
@Component
public class ProductClientFallback implements ProductClient {

    private static Logger logger = LoggerFactory.getLogger(ProductClientFallback.class);

    @Override
    public String findById(int id) {
        logger.info("*** productClient 调用 product-service.findById 异常！");
        return null;
    }
}
