package com.example.products9998.controller;

import com.example.products9998.entity.Product;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * @author :  yuhao
 * @date: 2020/10/28
 * @description:
 */
@RestController
@Slf4j
public class ProductController {

    @Value("${server.port}")
    private int port;

    // 测试  熔断机制
    @GetMapping("/product/break")
    @HystrixCommand(fallbackMethod = "testBreakFallBack")
    public String testBreak(@RequestParam("id") Integer id){
        if (id < 0){
            throw new RuntimeException("非法参数，id不能小于0");
        }
        return "访问成功，id：" + id;
    }

    // 触发熔断的fallback方法
    public String testBreakFallBack(Integer id){
        return "当前传入的参数id。不是有效参数，触发熔断";
    }

    @PostMapping("/product/save")
    public Map<String, Object> save(@RequestBody Product product){
        HashMap<String, Object> map = new HashMap<>();
        log.info("商品服务，接收到了商品信息：[{}]", product);
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        map.put("status", true);
        map.put("mag", "根据商品id查询商品信息成功:当前服务端口:" + port);
        map.put("product", product);
        return map;
    }

    @GetMapping("/product/findOne")
    public Map<String, Object> findOne(String productId){
        HashMap<String, Object> map = new HashMap<>();
        if (Integer.parseInt(productId) < 0){
            throw new RuntimeException("商品id不能小于0");
        }
        log.info("商品服务，接收到了商品id：[{}]", productId);
        map.put("status", true);
        map.put("mag", "根据商品id查询商品信息成功");
        map.put("productId", productId);
        return map;
    }

    @GetMapping("/product/showMsg")
    public String showMsg(){
        log.info("进入商品服务，展示商品信息");
        return "进入商品服务，展示商品信息==="+ "当前服务端口" + port;
    }

    // 由于Rest风格的存在，返回的Map会被转化为Json
    @GetMapping("/product/findAll")
    public Map<String, Object> findAll(){
        Map<String, Object> map = new HashMap<>();
        log.debug("进入商品服务，查询所有商品信息...");
        map.put("status", true);
        map.put("msg", "查询所有商品信息成功！ 当前服务端口:" + port);
        return map;
    }
}
