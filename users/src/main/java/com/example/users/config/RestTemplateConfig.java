package com.example.users.config;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * @author :  yuhao
 * @date: 2020/10/28
 * @description:
 */
@Configuration
public class RestTemplateConfig {

    @Bean
    @LoadBalanced  // 代表带有ribbon负载均衡的restTemplate客户端对象
    public RestTemplate getRestTemplate(){
        return new RestTemplate();
    }
}
