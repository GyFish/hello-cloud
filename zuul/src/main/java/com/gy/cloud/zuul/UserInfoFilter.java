package com.gy.cloud.zuul;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

import static org.springframework.cloud.netflix.zuul.filters.support.FilterConstants.PRE_TYPE;

/**
 * Created by gy on 2018/6/2.
 */
@Component
public class UserInfoFilter extends ZuulFilter {
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
        System.out.println("1. 获取用户信息");

        RequestContext ctx = RequestContext.getCurrentContext();

        // 获取 user info
        HttpServletRequest request = ctx.getRequest();

        String tenant = request.getParameter("tenant");

        // 设置
        ctx.set("tenant", tenant);

        return null;
    }
}
