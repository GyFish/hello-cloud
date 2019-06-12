package com.gy.cloud.com.gy.coud.gateway.client;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;

/**
 * @author geyu
 */
@Component
@Slf4j
public class ServiceClient {

    private WebClient client = WebClient.builder()
            .baseUrl("http://localhost:7001/manager")
            .build();


    /**
     * mysql 插入数据，返回 uuid
     */
    public void saveFormInfo(Object info) {

        log.info(">> saveFormInfo");

        client.post()
                .uri("/form/saveFormInfo")
                .syncBody(info)
                .retrieve()
                .bodyToMono(String.class)
                .subscribe(log::info);
    }

    /**
     * 管理端保存流程定义
     */
    public void saveFlowInfo(Object info) {

        log.info(">> saveFlowInfo");

        Mono<JSONObject> res = client.post()
                .uri("/flow/saveFlowInfo")
                .syncBody(info)
                .retrieve()
                .bodyToMono(JSONObject.class);

        res.subscribe(data -> log.info("<< saveFlowInfo.res = {}", JSON.toJSONString(data, true)));
    }

}
