package com.gy.cloud.zuul.filter;

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
public class TenantFilter extends ZuulFilter {

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

      System.out.println("3. 根据租户选择服务");

      if (true)
          return true;

      RequestContext ctx = RequestContext.getCurrentContext();

      String tenant = String.valueOf(ctx.get("tenant"));

      String serviceHello = "service_hello";
      String serviceHi = "service_hi";

      String serviceHost = "http://localhost:7100";

      if ("hello".equals(tenant))
        ctx.set(SERVICE_ID_KEY, serviceHello);

      if ("hi".equals(tenant))
        ctx.set(SERVICE_ID_KEY, serviceHi);

      if ("".equals(tenant))
        ctx.set(SERVICE_ID_KEY, null);

      return null;
    }
}
