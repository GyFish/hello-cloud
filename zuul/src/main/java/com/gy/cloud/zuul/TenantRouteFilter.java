package com.gy.cloud.zuul;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import org.springframework.stereotype.Component;

import java.net.MalformedURLException;
import java.net.URL;

import static org.springframework.cloud.netflix.zuul.filters.support.FilterConstants.PRE_TYPE;
import static org.springframework.cloud.netflix.zuul.filters.support.FilterConstants.SERVICE_ID_KEY;

/**
 * Created by gy on 2018/6/2.
 */
@Component
public class TenantRouteFilter extends ZuulFilter {
    @Override
    public String filterType() {
        return PRE_TYPE;
    }

    @Override
    public int filterOrder() {
        return 3;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() {

        System.out.println("3. 路由决策");

        RequestContext ctx = RequestContext.getCurrentContext();

        String tenant = String.valueOf(ctx.get("tenant"));
        String serviceId = "service_hello";
        String serviceHost = "http://localhost:7100";

        if ("geyu".equals(tenant)) {
            ctx.set(SERVICE_ID_KEY, serviceId);
//            try {
//                ctx.setRouteHost(new URL(serviceHost));
//            } catch (MalformedURLException e) {
//                e.printStackTrace();
//            }
        }

        return null;
    }
}
