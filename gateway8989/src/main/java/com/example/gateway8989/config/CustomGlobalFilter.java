package com.example.gateway8989.config;

import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.logging.Logger;

/**
 * @author :  yuhao
 * @date: 2020/11/2
 * @description: 自定义全局filter
 */
@Configuration
public class CustomGlobalFilter implements GlobalFilter, Ordered {

    @Bean
    public GlobalFilter customFilter(){
        return new CustomGlobalFilter();
    }

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        Logger logger = Logger.getLogger("CustomGlobalFilter");
        logger.info("custom global filter");
        if (exchange.getRequest().getQueryParams().get("username") != null){
            logger.info("用户身份信息合法");
            return chain.filter(exchange);
        }
        return exchange.getResponse().setComplete();
    }

    @Override
    public int getOrder() {
        return -1;
    }
}
