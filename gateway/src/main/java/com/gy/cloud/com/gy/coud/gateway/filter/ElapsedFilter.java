package com.gy.cloud.com.gy.coud.gateway.filter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
 * 请求用时
 */
@Slf4j
public class ElapsedFilter implements GlobalFilter, Ordered {

  private static final String ELAPSED_TIME_BEGIN = "elapsedTimeBegin";

  @Override
  public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {

    log.info(">> welcome to elapsed filter!");

    exchange.getAttributes().put(ELAPSED_TIME_BEGIN, System.currentTimeMillis());

    return chain.filter(exchange).then(
            Mono.fromRunnable(() -> {
              Long startTime = exchange.getAttribute(ELAPSED_TIME_BEGIN);
              if (startTime != null) {
                log.info(">> 请求耗时：" + exchange.getRequest().getURI().getRawPath() + " = " + (System.currentTimeMillis() - startTime) + "ms");
              }
            })
    );
  }

  @Override
  public int getOrder() {
    return Ordered.LOWEST_PRECEDENCE;
  }
}