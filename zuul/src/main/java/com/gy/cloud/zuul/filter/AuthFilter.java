package com.gy.cloud.zuul.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

import static org.springframework.cloud.netflix.zuul.filters.support.FilterConstants.PRE_TYPE;

/**
 * Created by gy on 2018/6/2.
 */
@Component
public class AuthFilter extends ZuulFilter {

    @Override
    public String filterType() {
        return PRE_TYPE;
    }

    @Override
    public int filterOrder() {
        return 1;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() {

        System.out.println("1. 认证");

        // 获取上下文
        RequestContext ctx = RequestContext.getCurrentContext();

        String tenant = null;

        // 从请求中获取租户
        HttpServletRequest request = ctx.getRequest();
        tenant = request.getParameter("tenant");

        ctx.set("tenant", tenant == null ? "" : tenant);

        return null;
    }
}
