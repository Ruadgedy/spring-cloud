package com.example.users.controller;

import com.example.users.entity.Product;
import com.example.users.feignclients.ProductClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * @author :  yuhao
 * @date: 2020/10/28
 * @description:
 */
@RestController
@Slf4j
public class FeignController {

    @Autowired
    private ProductClient productClient;

    @GetMapping("/feign/test")
    public void testFeign(){
        log.info("进入测试feign调用的方法");
        String msg = productClient.showMsg();
        log.info("调用商品服务，返回的信息：[{}]",msg);

        String all = productClient.findAll();
        log.info("调用查询所有，返回的信息：[{}]",all);
    }

    @GetMapping("/feign/test1")
    public Map<String, Object> test1(@RequestParam("id") String id){
        log.info("进入feign调用传参的情况测试");
        Map<String, Object> one = productClient.findOne(id);
        log.info("调用查询一个请求，返回: [{}]", one);

        return one;
    }

    @PostMapping("/feign/test2")
    public Map<String, Object> test2(@RequestBody Product product){
        log.info("进入feign调用传参的情况测试");
        Map<String, Object> one = productClient.save(product);
        log.info("调用查询一个请求，返回: [{}]", one);

        return one;
    }
}
