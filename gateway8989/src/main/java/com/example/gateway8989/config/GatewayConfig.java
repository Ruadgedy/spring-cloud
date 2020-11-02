package com.example.gateway8989.config;

import org.springframework.cloud.gateway.route.Route;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.PredicateSpec;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.cloud.gateway.support.ServerWebExchangeUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.server.ServerWebExchange;

import java.util.function.Function;

/**
 * @author :  yuhao
 * @date: 2020/11/1
 * @description:
 */
@Configuration
public class GatewayConfig {

    // 通过 java 代码 配置路由规则，但是不建议用java代码配置
//    @Bean
    public RouteLocator customRouteLocator(RouteLocatorBuilder builder){
        return builder.routes()
                .route("product_route", r -> r.path("/product/**").uri("http://localhost:9998"))
                .route("user_route", new Function<PredicateSpec, Route.AsyncBuilder>() {
                    @Override
                    public Route.AsyncBuilder apply(PredicateSpec predicateSpec) {
                        return predicateSpec.path("/feign/**").uri("http://localhost:9999");
                    }
                }).build();
    }
}
