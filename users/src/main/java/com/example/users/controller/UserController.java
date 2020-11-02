package com.example.users.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;

/**
 * @author :  yuhao
 * @date: 2020/10/28
 * @description:
 */
@RestController
@Slf4j
public class UserController {

    @Autowired
    private DiscoveryClient discoveryClient;

    @Autowired
    private LoadBalancerClient loadBalancerClient;

    @Autowired
    private RestTemplate restTemplate;  // 带有负载均衡的restTemplate

    @GetMapping("/user/showProductMsg")
    public String showProductMsg(){
        RestTemplate restTemplate = new RestTemplate();
        String object = restTemplate.getForObject("http://localhost:9998/product/showMsg", String.class);
        log.info("调用成功返回的信息：[{}]", object);
        return object;
    }

    @GetMapping("/user/findAllProduct")
    public String findAllProduct(){
        log.debug("进入用户服务");
        // 1. 使用restTemplate方式直接调用
//        RestTemplate restTemplate = new RestTemplate();
//        String forObject = restTemplate.getForObject("http://localhost:9998/product/findAll", String.class);
//        log.debug("商品服务调用返回结果：[{}]", forObject);

        // 2. 使用ribbon调用方式:有三种方式： I: discoveryclient    II: loadblanceclient    III:  @LoadBalance
        // discoveryClient会将全部服务返回
        List<ServiceInstance> products = discoveryClient.getInstances("products");
        for (ServiceInstance serviceInstance : products) {

            System.out.println(serviceInstance.getHost());
            System.out.println(serviceInstance.getPort());
        }

        // 3. 使用 loadbalance client  默认是轮训  拿到服务器
//        ServiceInstance serviceInstance = loadBalancerClient.choose("products");
//        System.out.println(serviceInstance.getPort());
//        System.out.println(serviceInstance.getHost());
//        RestTemplate restTemplate = new RestTemplate();
//        String uri = serviceInstance.getUri().toString() + "/product/findAll";
//        String forObject = restTemplate.getForObject(uri, String.class);

        // 4. @loadBalance + RestTemplate
        String forObject = restTemplate.getForObject("http://products/product/findAll", String.class);


        return forObject;
    }
}
