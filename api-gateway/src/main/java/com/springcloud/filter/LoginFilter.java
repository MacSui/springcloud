package com.springcloud.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;

/**
 * @Description:
 * @Author: Sui, ChengBin
 * @Date: 2020/6/22
 **/
@Component
public class LoginFilter extends ZuulFilter {
    @Override
    public String filterType() {
        return FilterConstants.PRE_TYPE;
    }

    @Override
    public int filterOrder() {
        return 0;
    }

    @Override
    public boolean shouldFilter() {
        RequestContext rc = RequestContext.getCurrentContext();
        HttpServletRequest request = rc.getRequest();
        String uri = request.getRequestURI();
        //保存方法进行登录验证
        if (!StringUtils.isEmpty(uri) && uri.indexOf("save")>-1){
            return true;
        }
        return false;
    }

    @Override
    public Object run(){
        RequestContext requestContext = RequestContext.getCurrentContext();
        HttpServletRequest request = requestContext.getRequest();
        String token = request.getHeader("token");
        String cookie = request.getHeader("Cookie");
        System.out.println("request header token:"+token+",Cookie:"+cookie);
        if (StringUtils.isEmpty(token)){
            token = request.getParameter("token");
            System.out.println("request parameter token:"+token);
        }
//        String s = null;
//        s.equals("a");
        if (StringUtils.isEmpty(token)){
            requestContext.setSendZuulResponse(false);
            requestContext.setResponseStatusCode(HttpStatus.UNAUTHORIZED.value());
        }
        return null;
    }
}
