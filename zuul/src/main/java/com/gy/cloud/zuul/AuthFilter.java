package com.gy.cloud.zuul;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import org.springframework.stereotype.Component;

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
        return 2;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() {

        System.out.println("2. 校验权限");

        RequestContext ctx = RequestContext.getCurrentContext();

        if (ctx.get("user") == null)
            return "who are you";

        return null;
    }
}
