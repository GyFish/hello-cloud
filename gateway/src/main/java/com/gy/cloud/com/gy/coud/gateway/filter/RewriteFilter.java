package com.gy.cloud.com.gy.coud.gateway.filter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.cloud.gateway.route.Route;
import org.springframework.core.Ordered;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.net.URI;

import static org.springframework.cloud.gateway.support.ServerWebExchangeUtils.GATEWAY_REQUEST_URL_ATTR;
import static org.springframework.cloud.gateway.support.ServerWebExchangeUtils.GATEWAY_ROUTE_ATTR;

@Slf4j
public class RewriteFilter implements GlobalFilter, Ordered {

  @Override
  public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {

    log.info(">> welcome to rewrite filter!");

    // 原始请求
    ServerHttpRequest req = exchange.getRequest();
    URI uri = req.getURI();
    String path = uri.getPath();

    // 原始 route
    Route route = exchange.getAttribute(GATEWAY_ROUTE_ATTR);

    // 新的 path
    int offset = path.indexOf("/", 1);
    String newPath = path.substring(offset);

    // 构建新请求
    ServerHttpRequest request = req.mutate()
            .path(newPath)
            .build();

    // 新 url
    assert route != null;
    URI newUri = URI.create(route.getUri().toString() + newPath);

    // GATEWAY_REQUEST_URL_ATTR 是 NettyRoutingFilter 最终的请求地址
    exchange.getAttributes().put(GATEWAY_REQUEST_URL_ATTR, newUri);

    return chain.filter(exchange.mutate().request(request).build());
  }

  @Override
  public int getOrder() {
    return Ordered.LOWEST_PRECEDENCE;
  }
}
