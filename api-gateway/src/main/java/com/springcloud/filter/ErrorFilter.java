package com.springcloud.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletResponse;

/**
 * @Description:
 * @Author: Sui, ChengBin
 * @Date: 2020/6/24
 **/
@Component
public class ErrorFilter extends ZuulFilter {

    private static final Logger logger = LoggerFactory.getLogger(ErrorFilter.class);

    @Override
    public String filterType() {
        return FilterConstants.ERROR_TYPE;
    }

    @Override
    public int filterOrder() {
        return 10;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() {
        logger.info("ErrorFilter.run method");
        RequestContext currentContext = RequestContext.getCurrentContext();
        Throwable throwable = currentContext.getThrowable();
        logger.error("this is a ErrorFilter: {}", throwable.getCause().getMessage());
        currentContext.set("error.status_code", HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        currentContext.set("error.exception", throwable.getCause());
        return null;
    }
}
